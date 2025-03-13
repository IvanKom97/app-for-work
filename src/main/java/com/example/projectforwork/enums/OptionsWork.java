package com.example.projectforwork.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OptionsWork {

    JAVA(TypesRepairs.STUDYING),
    PITON(TypesRepairs.STUDYING),
    ENGLISH(TypesRepairs.STUDYING),
    JAVA_SCRIPT(TypesRepairs.STUDYING),
    READING(TypesRepairs.STUDYING),

    FIXING_TRANSMISION(TypesRepairs.REPAIRS_CARS),
    CLEANING_CAR(TypesRepairs.REPAIRS_CARS),
    SERVICE_CAR(TypesRepairs.REPAIRS_CARS),
    CHANGE_OIL(TypesRepairs.REPAIRS_CARS),
    SELLING_YOUR_CAR(TypesRepairs.REPAIRS_CARS),

    CLEANING_HOME(TypesRepairs.HOME_WORK),
    SEATING_WITH_CHILD(TypesRepairs.HOME_WORK),
    COOKING(TypesRepairs.HOME_WORK),
    REPAIRS_HOME(TypesRepairs.HOME_WORK),
    GARDEN_WORKER(TypesRepairs.HOME_WORK),

    PERSONAL_TRAINING(TypesRepairs.TRAINING),
    HOCKEY_TRAINER(TypesRepairs.SECURITY),
    FOOTBALL_TRAINER(TypesRepairs.TRAINING),
    RUNNING_TRAINER(TypesRepairs.TRAINING),
    BOXING_TRAINER(TypesRepairs.TRAINING),

    BODYGUARD(TypesRepairs.SECURITY),
    PRIVATE_SECURITY(TypesRepairs.SECURITY),
    TEACHING_SHOOTING(TypesRepairs.SECURITY),
    WATCHMAN(TypesRepairs.SECURITY);
    private final TypesRepairs typesRepairs;
}
