-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准', '2000', '1', 'standard', 'limit/standard/index', 1, 0, 'C', '0', '0', 'limit:standard:list', '#', 'admin', sysdate(), '', null, '检测标准菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'limit:standard:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'limit:standard:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'limit:standard:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'limit:standard:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('检测标准导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'limit:standard:export',       '#', 'admin', sysdate(), '', null, '');