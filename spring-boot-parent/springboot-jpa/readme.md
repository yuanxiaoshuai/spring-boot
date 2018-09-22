
#创建jpa 使用自动创建时候注意配置
spring.jpa.properties.hibernate.hbm2ddl.auto=create

validate               加载hibernate时，验证创建数据库表结构
create                  每次加载hibernate，重新创建数据库表结构，这就是导致数据库表数据丢失的原因。
create-drop        加载hibernate时创建，退出是删除表结构
update                 加载hibernate自动更新数据库结构