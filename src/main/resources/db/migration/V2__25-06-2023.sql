select constraint_name from information_schema.constraint_column_usage
where table_name = 'user_roles' and column_name = 'role_id'
and constraint_name <> 'unique_role_user';

alter table user_roles drop CONSTRAINT 'uk_5q4rc4fh1on6567qk69uesvyf';