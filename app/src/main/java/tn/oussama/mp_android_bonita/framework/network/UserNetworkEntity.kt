package tn.oussama.mp_android_bonita.framework.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//GET A USER INFOS ==> /API/identity/professionalcontactdata/:userId
//or
///API/identity/personalcontactdata/:userId


///API/bpm/actor/:actorId -> get actor infos

/*LOGIN URL
   curl -v -c saved_cookies.txt -X POST --url 'http://localhost:8080/bonita/loginservice' \
       --header 'Content-Type: application/x-www-form-urlencoded; charset=utf-8' -O /dev/null \
        -d 'username=walter.bates&password=bpm&redirect=false&redirectURL='

* LOGOUT call -->
    curl -b saved_cookies.txt -X GET --url 'http://localhost:8080/bonita/logoutservice?redirect=false'
* */
data class UserNetworkEntity (
    @SerializedName("user_id")
    @Expose
    var id: Long,

    @SerializedName("user_name")
    @Expose
    var userName: String,

    @SerializedName("is_guest_user")
    @Expose
    var isGuestUser: Boolean,

    @SerializedName("is_technical_user")
    @Expose
    var isTechnicalUser: Boolean,

) {}

/*
*   "userName":"giovanna.almeida",
  "lastname":"Almeida",
  "firstname":"Giovanna",
* */

/*
* {
  "last_connection":"",
  "created_by_user_id":"-1",
  "creation_date":"2014-12-01 10:39:55.177",
  "id":"21",
  "icon":"/default/icon_user.png",
  "enabled":"true",
  "title":"Mrs",
  "professional_data":{
    "fax_number":"484-302-0430",
    "building":"70",
    "phone_number":"484-302-5430",
    "website":"",
    "zipcode":"19108",
    "state":"PA",
    "city":"Philadelphia",
    "country":"United States",
    "id":"21",
    "mobile_number":"",
    "address":"Renwick Drive",
    "email":"giovanna.almeida@acme.com",
    "room":""
  },
  "manager_id":{
    "last_connection":"",
    "created_by_user_id":"-1",
    "creation_date":"2014-12-01 10:39:55.136",
    "id":"17",
    "icon":"/default/icon_user.png",
    "enabled":"true",
    "title":"Mrs",
    "manager_id":"1",
    "job_title":"Vice President of Sales",
    "userName":"daniela.angelo",
    "lastname":"Angelo",
    "firstname":"Daniela",
    "password":"",
    "last_update_date":"2014-12-01 10:39:55.136"
  },
  "job_title":"Account manager",
  "userName":"giovanna.almeida",
  "lastname":"Almeida",
  "firstname":"Giovanna",
  "password":"",
  "last_update_date":"2014-12-01 10:39:55.177"
}
*
*
*
*
* */