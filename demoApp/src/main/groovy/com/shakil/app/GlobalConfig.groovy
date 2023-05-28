package com.shakil.app

class GlobalConfig {
    public static final def USER_ROLE = [
            ADMINISTRATOR : "ADMINISTRATOR",
            REGULAR_MEMBER : "REGULAR_MEMBER",
    ]

    public static itemsPerPage(){
        return 5
    }
}
