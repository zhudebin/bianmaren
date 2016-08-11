package com.bianmaren.dao.impl;

import com.bianmaren.dao.ArticleCategoryDao;
import com.bianmaren.entity.ArticleCategory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("articleCategoryDaoImpl")
public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategory, Long> implements ArticleCategoryDao {

	public List<ArticleCategory> findRoots(Integer count) {
		String jpql = "select articleCategory from ArticleCategory articleCategory where articleCategory.parent is null order by articleCategory.order asc";
		TypedQuery<ArticleCategory> query = entityManager.createQuery(jpql, ArticleCategory.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<ArticleCategory> findParents(ArticleCategory articleCategory, Integer count) {
		if (articleCategory == null || articleCategory.getParent() == null) {
			return Collections.<ArticleCategory> emptyList();
		}
		String jpql = "select articleCategory from ArticleCategory articleCategory where articleCategory.id in (:ids) order by articleCategory.grade asc";
		TypedQuery<ArticleCategory> query = entityManager.createQuery(jpql, ArticleCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", articleCategory.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<ArticleCategory> findChildren(ArticleCategory articleCategory, Integer count) {
		TypedQuery<ArticleCategory> query;
		if (articleCategory != null) {
			String jpql = "select articleCategory from ArticleCategory articleCategory where articleCategory.treePath like :treePath order by articleCategory.order asc";
			query = entityManager.createQuery(jpql, ArticleCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select articleCategory from ArticleCategory articleCategory order by articleCategory.order asc";
			query = entityManager.createQuery(jpql, ArticleCategory.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), articleCategory);
	}

	@Override
	public void persist(ArticleCategory articleCategory) {
		Assert.notNull(articleCategory);
		setValue(articleCategory);
		super.persist(articleCategory);
	}

	@Override
	public ArticleCategory merge(ArticleCategory articleCategory) {
		Assert.notNull(articleCategory);
		setValue(articleCategory);
		for (ArticleCategory category : findChildren(articleCategory, null)) {
			setValue(category);
		}
		return super.merge(articleCategory);
	}

	private List<ArticleCategory> sort(List<ArticleCategory> articleCategories, ArticleCategory parent) {
		List<ArticleCategory> result = new ArrayList<ArticleCategory>();
		if (articleCategories != null) {
			for (ArticleCategory articleCategory : articleCategories) {
				if ((articleCategory.getParent() != null && articleCategory.getParent().equals(parent)) || (articleCategory.getParent() == null && parent == null)) {
					result.add(articleCategory);
					result.addAll(sort(articleCategories, articleCategory));
				}
			}
		}
		return result;
	}

	private void setValue(ArticleCategory articleCategory) {
		if (articleCategory == null) {
			return;
		}
		ArticleCategory parent = articleCategory.getParent();
		if (parent != null) {
			articleCategory.setTreePath(parent.getTreePath() + parent.getId() + ArticleCategory.TREE_PATH_SEPARATOR);
		} else {
			articleCategory.setTreePath(ArticleCategory.TREE_PATH_SEPARATOR);
		}
		articleCategory.setGrade(articleCategory.getTreePaths().size());
	}

}