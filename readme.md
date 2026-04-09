# ParcelDelivery

## Описание

Проект по загрузке и разгрузке грузовиков посылками.  
Поиск, создание и удаление посылок в репозитории.  
Работа через телеграм бота JavaCourceParcelDeliveryBot или через консоль.

## Настройки

1. В application.properties задать настройки телеграм бота(имя и токен) и интерфейс использования: консоль или Telegram.

## Использование

### Работа с репозиторием

1. Создание посылки  
   ВХОД  
   `create -name "Квадратное колесо" -form "xxx\nx x\nxxx"`  
   ВЫХОД

```  
  name: "Квадратное колесо"  
  form:
  ooo  
  o o
  ooo
```

2. Просмотр посылки  
   ВХОД  
   `find "Parcel type 4"`  
   ВЫХОД

```
  name: "Parcel type 4"
  form:
  4444
```

3. Просмотр всех посылок  
   ВХОД  
   `findAll`  
   ВЫХОД

```
  name: "Parcel type 4"
  form:
  4444
  name: "Parcel type 5"
  form:
  55555
```
4. Удаление посылки  
ВХОД  
`delete "Parcel type 4"`  
ВЫХОД  
`"Посылка Тип 4" удалена`

### Работа с загрузкой грузовиков
1. Вход-выход текст  
ВХОД  
   `load -parcels -text "Parcel type 9\nParcel type 6\nParcel type 5\nParcel type 1\nParcel type 1" -trucks 5 -type TOWER -out text`  
ВЫХОД   
```
+666   +
+666   +
+999   +
+999   +
+999   +
+55555 +
++++++++
Parcel type 5:
[[5, 5, 5, 5, 5]]
---------------
Parcel type 9:
[[9, 9, 9], [9, 9, 9], [9, 9, 9]]
---------------
Parcel type 6:
[[6, 6, 6], [6, 6, 6]]
---------------

+      +
+      +
+      +
+      +
+1     +
+1     +
++++++++
Parcel type 1:
[[1]]
---------------
Parcel type 1:
[[1]]
---------------
```
2. Вход-выход файл  
ВХОД  
`load -parcels -file "C:\parcels.txt" -trucks 5 -type TOWER -out json-file -out-filename "truckTest.json"`  
parcels.txt  
Parcel type 9  
Parcel type 6  
Parcel type 5  
Parcel type 1  
Parcel type 1  
Parcel type 3  
Parcel type 7  
ВЫХОД  
```
[ {
  "truckSpace" : [ [ "5", "5", "5", "5", "5", null ], [ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ] ],
  "width" : 6,
  "height" : 6,
  "parcels" : [ {
    "height" : 1,
    "width" : 5,
    "form" : [ [ "5", "5", "5", "5", "5" ] ],
    "name" : "Parcel type 5"
  }, {
    "height" : 2,
    "width" : 4,
    "form" : [ [ "7", "7", "7", "7" ], [ "7", "7", "7" ] ],
    "name" : "Parcel type 7"
  }, {
    "height" : 3,
    "width" : 3,
    "form" : [ [ "9", "9", "9" ], [ "9", "9", "9" ], [ "9", "9", "9" ] ],
    "name" : "Parcel type 9"
  } ]
}, {
  "truckSpace" : [ [ "6", "6", "6", null, null, null ], [ "6", "6", "6", null, null, null ], [ "3", "3", "3", null, null, null ], [ "1", null, null, null, null, null ], [ "1", null, null, null, null, null ], [ null, null, null, null, null, null ] ],
  "width" : 6,
  "height" : 6,
  "parcels" : [ {
    "height" : 2,
    "width" : 3,
    "form" : [ [ "6", "6", "6" ], [ "6", "6", "6" ] ],
    "name" : "Parcel type 6"
  }, {
    "height" : 1,
    "width" : 3,
    "form" : [ [ "3", "3", "3" ] ],
    "name" : "Parcel type 3"
  }, {
    "height" : 1,
    "width" : 1,
    "form" : [ [ "1" ] ],
    "name" : "Parcel type 1"
  }, {
    "height" : 1,
    "width" : 1,
    "form" : [ [ "1" ] ],
    "name" : "Parcel type 1"
  } ]
} ]
```
### Работа с разгрузкой грузовиков
1. Разгрузка с подсчетом  
ВХОД  
`unload -infile "C:\trucks.json" -outfile "parcels.txt" -withcount`  
ВЫХОД  
   parcels.txt
```
"Parcel type 3";1
"Parcel type 6";1
"Parcel type 5";1
"Parcel type 1";2
"Parcel type 7";1
"Parcel type 9";1
```
2. Разгрузка без подсчета  
ВХОД  
`unload -infile "C:\trucks.json" -outfile "parcels.txt"`  
   ВЫХОД  
   parcels.txt
```
"Parcel type 3"
"Parcel type 6"
"Parcel type 5"
"Parcel type 1"
"Parcel type 7"
"Parcel type 9"
```
