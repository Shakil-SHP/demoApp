package com.shakil.app

class AppInitializationService {

    static initialize() {
        initMember()
    }

    private static initMember() {
        if (Member.count() == 0) {
            Member member = new Member()
            member.firstName = "System"
            member.lastName = "Administrator"
            member.email = "demo@demo.com"
            member.password = "123456"
            member.memberRole = GlobalConfig.USER_ROLE.ADMINISTRATOR
            member.save(flash: true)
        }
    }
}
