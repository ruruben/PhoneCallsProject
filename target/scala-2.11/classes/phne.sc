val cals = """00:01:07,400-234-090
              00:05:01,701-080-080
              00:05:00,400-234-090"""
def money(time:String): Int ={
  var horas = time.split(":")(0).toInt
  var minutos = time.split(":")(1).toInt
  var segundos = time.split(":")(2).toInt
  if(minutos >= 5)
    if(segundos >= 1)
      (horas * 60 + minutos + 1) * 150
    else
      (horas * 60 + minutos) * 150
  else
    (minutos * 60 + segundos) * 3
}
def llamadas = cals.split("\n")

def tuplas = llamadas.map(x => (x.split(",")(1).substring(0,11),x.split(",")(0).trim()) )
def sol = tuplas.map(x => (x._1, money(x._2)))
def filter = sol.filter(_._1 == "400-234-090")
def lista = filter.map((x=> x._2) )
print(lista.ex ("400-234-090"))