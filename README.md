iDong
=====



User
================
Long    uid (PRI)
String  username
String  nickname
String  idname
String  email
String  sex
String  phone_num
Date    birthday
String  qq
String  weibo
String  interest (e.g. 1,2,3)
String  address
String  company
String  schoole
String  major
String  image_url (list)

Long    user_account (FK)

User Account
================
Long    id (PRI)
String  password
String  auth_email

Party
================
Long    pid (PRI)
Long    laucher_uid (FK)
String  title
Long    category
Date    start_time
Date    end_time
String  description
String  location
Long    stadium_id (FK)
String  image_url (list)

User-Party Mapping
================
Long    id (PRI)
Long    uid (FK)
Long    pid (FK)
Long    flag

Stadium
================
Long    stadium_id (PRI)

String  country
String  privince
String  city
String  district
String  street
String  address_prefix

Long    price
Long    vote

String  image_url (list)


Stadium Support Category
================
Long    stadium_id (PRI)
Long    category
