function getDate(date){
    var dateL = new Date();
    if(date){
       dateL = date;
    }
    /*
    * 10:10:25 10.12.10
    * */
    return dateL.getHours() + ":" + dateL.getMinutes() + ":" + dateL.getSeconds() + " " +
            dateL.getDay
}