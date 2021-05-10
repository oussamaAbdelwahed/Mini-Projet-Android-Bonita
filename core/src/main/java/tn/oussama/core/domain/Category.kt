package tn.oussama.core.domain

data class Category
    (
      val id: Long,
      val displayName:String,
      val name:String,
      val creationDate: String?,
      val description:String?=""
    ) {}

/*
[
    {
        "createdBy": "1",
        "displayName": "Services des stages",
        "name": "Services des stages",
        "description": "",
        "creation_date": "2021-04-13 09:09:59.145",
        "id": "1"
    }
]
 */