package com.example.ejercicionumerosecreto

class funcionesVarias {
    companion object {

        fun checkEmptyFiedls(user:String,pass:String) : Boolean
        {
            var flag = true
            if (user == "" || pass == "") flag = false
            return flag
        }



    }

}