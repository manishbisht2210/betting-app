INSERT INTO `oauth_client_details` VALUES ('adminapp','mw/adminapp,ms/admin,ms/user','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','role_admin,role_superadmin','authorization_code,password,refresh_token,implicit',NULL,NULL,900,3600,'{}',NULL);

INSERT INTO `permission` VALUES (1,'can_delete_user','1970-01-01 00:00:00','1970-01-01 00:00:00',0),(2,'can_create_user','1970-01-01 00:00:00','1970-01-01 00:00:00',0),(3,'can_update_user','1970-01-01 00:00:00','1970-01-01 00:00:00',0),(4,'can_read_user','1970-01-01 00:00:00','1970-01-01 00:00:00',0);

INSERT INTO `role` VALUES (1,'role_admin','1970-01-01 00:00:00','1970-01-01 00:00:00',0),(2,'role_user','1970-01-01 00:00:00','1970-01-01 00:00:00',0);

INSERT INTO `user` VALUES (1,'7896534567','{bcrypt}$2a$04$EZzbSqieYfe/nFWfBWt2KeCdyq0UuDEM1ycFF8HzmlVR6sbsOnw7u','admin@example.com', 'Aditya', '05/12/1990',_binary '',_binary '\0',_binary '\0',_binary '\0','1970-01-01 00:00:00','1970-01-01 00:00:00',0),(2,'7634512789','{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi','user@example.com', 'UserXYZ', '05/12/1980',_binary '',_binary '\0',_binary '\0',_binary '\0','1970-01-01 00:00:00','1970-01-01 00:00:00',0);

INSERT INTO `permission_role` VALUES (1,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),(2,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),(3,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),(4,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),(4,2,'1970-01-01 00:00:00','1970-01-01 00:00:00',0);

INSERT INTO `role_user` VALUES (1,1,'1970-01-01 00:00:00','1970-01-01 00:00:00',0),(2,2,'1970-01-01 00:00:00','1970-01-01 00:00:00',0);



