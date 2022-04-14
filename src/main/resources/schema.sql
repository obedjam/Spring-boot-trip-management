CREATE TABLE `users`
(
    `user_id` INTEGER NOT NULL,
    `user_name` VARCHAR(512) NOT NULL,
    `password` VARCHAR(512) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `dob` DATE NOT NULL,
    `phone` VARCHAR(10),
    PRIMARY KEY(`user_id`)
);

CREATE TABLE `trip`
(
    `trip_id` INTEGER NOT NULL,
    `trip_name` VARCHAR(512) NOT NULL,
    `destination` VARCHAR(100) NOT NULL,
    `trip_description` VARCHAR(512) NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    PRIMARY KEY(`trip_id`)
);

CREATE TABLE `trip_user_mapping`
(
    `trip_id` INTEGER NOT NULL,
    `user_id` INTEGER NOT NULL,
    `user_role` VARCHAR(10) NOT NULL,
    PRIMARY KEY(`trip_id`),
    PRIMARY KEY(`user_id`),
    FOREIGN KEY(`trip_id`),
    FOREIGN KEY(`user_id`)
);

CREATE TABLE `trip_activity`
(
    `trip_id` INTEGER NOT NULL,
    `activity_description` VARCHAR(512) NOT NULL,
    `location` VARCHAR(512) NOT NULL,
    `activity_time` TIME NOT NULL,
    `added_by` INTEGER NOT NULL,
    `activity_status` TINYINT NOT NULL,
    PRIMARY KEY(`trip_id`),
    FOREIGN KEY(`trip_id`)
);