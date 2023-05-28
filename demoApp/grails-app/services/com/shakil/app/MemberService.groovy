package com.shakil.app

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class MemberService {
    def save(GrailsParameterMap params) {
        Member member = new Member(params)
        def response = AppUtils.saveResponse(false, member)
        if(member.validate()){
            member.save(flush: true)
            if (!member.hasErrors()){
                response.isSuccess = true
            }
        }
        return response
    }

    def update(Member member, GrailsParameterMap params) {
        member.properties = params
        def response = AppUtils.saveResponse(false, member)
        if (member.validate()) {
            member.save(flush: true)
            if (!member.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }

    def getById(Serializable sid){
        return Member.get(sid)
    }

    def getAllMemberList(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Member> memberList = Member.createCriteria().list(params){
            if(params?.colName && params?.colValue){
                like(params.colName, "%" + params.colValue + "%")
            }
            if(!params.sort){
                order("id","desc")
            }
        }
        return [list : memberList, count: memberList.totalCount]
    }

    def delete(Member member){
        try{
            member.delete(flush:true)
        } catch(Exception e){
            println(e.getMessage())
            return false
        }
        return  true
    }
}
