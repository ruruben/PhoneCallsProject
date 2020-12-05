object CallsCost {

  def main(args: Array[String]): Unit = {
    val calls1 = """00:01:07,400-234-090
              00:05:01,701-080-080
              00:05:00,400-234-090"""
    val calls = """00:05:01,400-234-090
              00:05:00,401-080-080
              00:00:01,401-080-080
              00:00:01,400-080-080"""

    callsToCost(calls)

    def callsToCost(calls:String): Int ={

      //Transform input of string of all calls to Arrys of calls
      //Transform Arrays of calls to tuplas --> (phone, time)
      def callsTimeTuplas = calls.split("\n").map(x => (x.split(",")(1).substring(0,11),x.split(",")(0).trim()))

      //Transform Arrays of calls to tuplas --> (phone, time) : (String, String) => (phone, Cost, Secons) : (Int,Int,Int)
      def callsCostSecons = callsTimeTuplas.map(x => (phoneToInt(x._1), timeToMoney(x._2)._1, timeToMoney(x._2)._2))

      //Group by phone and sum all cost and times (Phone, Cost, Secons) : (Int,Int,Int)
      println("Input calls phone, cost and duration:")
      def groupByCalls = callsCostSecons.groupBy(_._1)
        .map{ case (k,v) => k -> (v.map(_._2).sum, v.map(_._3).sum) }
        .map(k=> (k._1 ,k._2._1,k._2._2))
      groupByCalls.map(println)

      val free = groupByCalls.filter(_._3 == groupByCalls.maxBy(_._3)._3).minBy(_._1)
      val result1 = groupByCalls.filter( _ != groupByCalls.filter(_._3 == groupByCalls.maxBy(_._3)._3).minBy(_._1))
      val total = result1.map(_._2).sum
      println("Phone except to pay: " + free._1)
      println("Calls and cost:")
      result1.map(x => println("Phone: " + x._1 + ", Cents: " + x._2))
      println("Total to pay: " + total + " cents")

      total
    }

    def timeToMoney(time:String): (Int, Int) ={
      val horas = time.split(":")(0).toInt
      val minutos = time.split(":")(1).toInt
      val segundos = time.split(":")(2).toInt
      val tiempo = horas * 3600 + minutos * 60 + segundos

      if(minutos >= 5)
        if(segundos >= 1) ((horas * 60 + minutos + 1) * 150, tiempo)
        else ((horas * 60 + minutos) * 150, tiempo)
      else ((minutos * 60 + segundos) * 3, tiempo)

    }

    def phoneToInt(phone:String): Int ={
      phone.split("-")(0).toInt * 1000000 + phone.split("-")(1).toInt * 1000 + phone.split("-")(2).toInt
    }
  }

}
