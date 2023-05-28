package com.shakil.app


class AuthenticationService {
    private static final String AUTHORIZED = "AUTHORIZED"

    def setMemberAuthorization(Member member) {
        def authorization = [isLoggedIn: true, member: member]
        AppUtils.getApplicationSession()[AUTHORIZED] = authorization
    }

    def doLogin(String email, String password){
        password = password.encodeAsMD5()
        Member member = Member.findByEmailAndPassword(email, password)
        if (member){
            setMemberAuthorization(member)
            return true
        }
        return false
    }

    boolean isAuthenticated(){
        def authorization = AppUtils.getApplicationSession()[AUTHORIZED]
        if (authorization && authorization.isLoggedIn){
            return true
        }
        return false
    }


    def getMember(){
        def authorization = AppUtils.getApplicationSession()[AUTHORIZED]
        return authorization?.member
    }


    def getMemberName(){
        def member = getMember()
        return "${member.firstName} ${member.lastName}"
    }

    def isAdministratorMember(){
        def member = getMember()
        if (member && member.memberRole == GlobalConfig.USER_ROLE.ADMINISTRATOR){
            return true
        }
        return false
    }
}
