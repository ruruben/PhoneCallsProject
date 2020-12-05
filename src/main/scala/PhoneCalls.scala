import org.apache.spark.{SparkConf, SparkContext}

object PhoneCalls {
  // Spark Configuration Object
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Ejercicio2")

    val sc = new SparkContext(conf)

    val fileTxt = "data/cals.txt"
    val fileRDD = sc.textFile(fileTxt)

    println(fileRDD.mkString(", "))
  }
}
