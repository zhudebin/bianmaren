表结构说明
===================================

**区域表** t_area

字段名称        | 字段类型      | 字段说明
----------------|---------------|------------
id              | Long          | 主键
create_date     | Date          | 创建日期
modify_date     | Date          | 编辑日期
area_code       | String        | 区域代码
area_name       | String        | 区域名称
parent_area_id  | Long          | 上级ID
area_level      | Long          | 区域层级
area_order      | Long          | 区域排序
area_name_en    | Long          | 区域名称-英文
area_shortname_en  | Long       | 区域名称-英文-缩写