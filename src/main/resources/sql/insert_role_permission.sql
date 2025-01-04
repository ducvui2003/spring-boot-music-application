BEGIN;
INSERT INTO `roles` (`id`, `name`)
VALUES (1, 'USER');
INSERT INTO `roles` (`id`, `name`)
VALUES (2, 'ADMIN');
COMMIT;


BEGIN;
INSERT INTO `permissions` (`id`, `name`)
VALUES (1, 'USER_CHANGE_PASSWORD');
INSERT INTO `permissions` (`id`, `name`)
VALUES (2, 'ORDER_GET_DETAIL');
INSERT INTO `permissions` (`id`, `name`)
VALUES (3, 'PROMOTION_GET');
INSERT INTO `permissions` (`id`, `name`)
VALUES (4, 'ORDER_DELETE');
INSERT INTO `permissions` (`id`, `name`)
VALUES (5, 'ORDER_SUBMIT');
INSERT INTO `permissions` (`id`, `name`)
VALUES (6, 'ORDER_CANCEL');
INSERT INTO `permissions` (`id`, `name`)
VALUES (7, 'ORDER_UPDATE');
INSERT INTO `permissions` (`id`, `name`)
VALUES (8, 'ORDER_GET');
INSERT INTO `permissions` (`id`, `name`)
VALUES (9, 'USER_READ_INFO');
INSERT INTO `permissions` (`id`, `name`)
VALUES (10, 'PROMOTION_USE');
INSERT INTO `permissions` (`id`, `name`)
VALUES (11, 'RATING_CREATE');
INSERT INTO `permissions` (`id`, `name`)
VALUES (12, 'USER_CHANGE_INFO');
INSERT INTO `permissions` (`id`, `name`)
VALUES (13, 'ORDER_CREATE');
COMMIT;

BEGIN;
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 1);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 1);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 2);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 2);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 3);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 5);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 6);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 7);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 8);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 8);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 9);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 9);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 10);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 11);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 12);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (2, 12);
INSERT INTO `permission_role` (`role_id`, `permission_id`)
VALUES (1, 13);
COMMIT;

