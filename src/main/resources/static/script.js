let now = new Date();
async function closeData(id, id_visit,email, val) {
    var str = roof.options[roof.selectedIndex].textContent.split(" ");
let cd=roof.options[roof.selectedIndex].textContent.split(" ");
     let d=roof.options[roof.selectedIndex].value;
 const visitor=  await   fetch("http://localhost:9090/getFIO/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+email)
    .then((response) => response.json())
        .catch((error) => {
          console.error("Failed to fetch visitor:", error);
          return null;
        });

      if (!visitor) return;

      let item2 = visitor[0].id_visit;

      fetch("http://localhost:9090/get/currentevent/"+item2)
            .then((response) => {
              if (!response.ok) {
                throw new Error("Failed to fetch visitor.");
              }
              return response.json();
            })
            .then((visitor) => {
             selType.options[1].selected=true;
              var newStatus="Присутствовал";
              fetch("http://localhost:9090/" + id +"/chstatus", {
                    method: "POST",
                    headers: {
                      "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                      status: newStatus
                    }),
                  })
                    .then((response) => {
                      if (!response.ok) {
                        throw new Error("Не удалось обновить информацию о посещении мероприятия.");
                      }
   updatevisitorTable(visitor[0].surname+" "+visitor[0].name+" "+visitor[0].patronymic,visitor[0].id_visit,visitor[0].email);
                    })
                    .catch((error) => {
                      console.error("Не удалось обновить информацию о посещении мероприятия:", error);
                    });
            })
            .catch((error) => {
              console.error("Failed to fetch visitor:", error);
            });
            if(val)
            {document.getElementById('overlay1').style.display = 'none';
            }
            else{
             document.getElementById('overlay2').style.display = 'none';
             }
  }



  // Функция для обновления таблицы с информацией о посещении мероприятиям
  function updatevisitorTable2(cd,d,emai,optionvalue) {
      var tableBody = document.querySelector("#visitorTable2 tbody");
      tableBody.innerHTML = ""; // Очищаем таблицу перед обновлением
switch(optionvalue)
     {
     case "all":{
        document.getElementById('Vis').textContent="Мероприятия";
 fetch("http://localhost:9090/getFIO/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+emai)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch visitor.");
        }
        return response.json();
      })
      .then((visitor1) => {

     // root1.textContent = visitorData.email;
         fetch("http://localhost:9090/get/id/"+d)
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to fetch visitor.");
          }
          return response.json();
        })
        .then((visitor) => {
          visitor.forEach((visitor) => {
            let date1 = new Date(visitor.started_at);

            let formattedDate1 = date1.getFullYear() + "-" +
                ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                ("0" + date1.getDate()).slice(-2) + " " +
                ("0" + date1.getHours()).slice(-2) + ":" +
                ("0" + date1.getMinutes()).slice(-2) + ":" +
                ("0" + date1.getSeconds()).slice(-2);

                let date2 = new Date(visitor.ended_at);

                let formattedDate2 = date2.getFullYear() + "-" +
                    ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                    ("0" + date2.getDate()).slice(-2) + " " +
                    ("0" + date2.getHours()).slice(-2) + ":" +
                    ("0" + date2.getMinutes()).slice(-2) + ":" +
                    ("0" + date2.getSeconds()).slice(-2);
            var row =
              "<tr>" +
              "<td>" +
              visitor.event +
              "</td>" +
              "<td>" +
              (formattedDate1) +
              "</td>" +
                  "<td>" +
                        (formattedDate2) +
                        "</td>" +
                            "<td>" +
                                  (visitor.status) +
                                  "</td>" +
                                      "<td>" +
                                            (visitor.comment) +
                                            "</td>" +
                                                "<td>" +
                                                      (visitor.mark) +
                                                      "</td>"

              "</tr>";
            tableBody.insertAdjacentHTML("beforeend", row);
             var buttonCell = document.createElement('td');
                    var button = document.createElement('button');
                    button.textContent = 'Обновить';

                    // Передаем параметры в функцию updatevisitor
                    button.onclick = function() { updatevisitor(visitor.id, d); };

                    buttonCell.appendChild(button);
                    tableBody.lastChild.appendChild(buttonCell);
          });
        })
        .catch((error) => {
          console.error("Failed to fetch visitor:", error);
        });
          }
              )
              .catch((error) => {
                console.error(
                  "Не удалось получить подробную информацию о посещении мероприятия:",
                  error
                );
              });
              break;
     }
      case "week" : {
       document.getElementById('Vis').textContent="Ближайшие мероприятия";
        let endDate = new Date();
        endDate.setDate(now.getDate() + 8);

          let formatte = endDate.getFullYear() + "-" +
                  ("0" + (endDate.getMonth() + 1)).slice(-2) + "-" +
                  ("0" + endDate.getDate()).slice(-2) + " " +
                  ("0" + endDate.getHours()).slice(-2) + ":" +
                  ("0" + endDate.getMinutes()).slice(-2) + ":" +
                  ("0" + endDate.getSeconds()).slice(-2);
                    console.log(formatte);
      fetch("http://localhost:9090/getDate/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+emai+"/"+formatte)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch visitor.");
        }
        return response.json();
      })
      .then((visitor1) => {
          visitor1.forEach((visitor) => {
            let date1 = new Date(visitor.started_at);

            let formattedDate1 = date1.getFullYear() + "-" +
                ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                ("0" + date1.getDate()).slice(-2) + " " +
                ("0" + date1.getHours()).slice(-2) + ":" +
                ("0" + date1.getMinutes()).slice(-2) + ":" +
                ("0" + date1.getSeconds()).slice(-2);

                let date2 = new Date(visitor.ended_at);

                let formattedDate2 = date2.getFullYear() + "-" +
                    ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                    ("0" + date2.getDate()).slice(-2) + " " +
                    ("0" + date2.getHours()).slice(-2) + ":" +
                    ("0" + date2.getMinutes()).slice(-2) + ":" +
                    ("0" + date2.getSeconds()).slice(-2);
            var row =
              "<tr>" +
              "<td>" +
              visitor.event +
              "</td>" +
              "<td>" +
              (formattedDate1) +
              "</td>" +
                  "<td>" +
                        (formattedDate2) +
                        "</td>" +
                            "<td>" +
                                  (visitor.status) +
                                  "</td>" +
                                      "<td>" +
                                            (visitor.comment) +
                                            "</td>" +
                                                "<td>" +
                                                      (visitor.mark) +
                                                      "</td>"

              "</tr>";
            tableBody.insertAdjacentHTML("beforeend", row);
             var buttonCell = document.createElement('td');
                    var button = document.createElement('button');
                    button.textContent = 'Обновить';

                    // Передаем параметры в функцию updatevisitor
                    button.onclick = function() { updatevisitor(visitor.id, d); };

                    buttonCell.appendChild(button);
                    tableBody.lastChild.appendChild(buttonCell);
          });
        })
         .catch((error) => {
                        console.error(
                          "Не удалось получить подробную информацию о посещении мероприятия:",
                          error
                        );
                      });
                      break;
          }



              case "threedays":{

                     document.getElementById('Vis').textContent="Ближайшие мероприятия";
                             let endDate = new Date();
                             endDate.setDate(now.getDate() + 4);
                          let formatte = endDate.getFullYear() + "-" +
                                         ("0" + (endDate.getMonth() + 1)).slice(-2) + "-" +
                                         ("0" + endDate.getDate()).slice(-2) + " " +
                                         ("0" + endDate.getHours()).slice(-2) + ":" +
                                         ("0" + endDate.getMinutes()).slice(-2) + ":" +
                                         ("0" + endDate.getSeconds()).slice(-2);
                             fetch("http://localhost:9090/getDate/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+emai+"/"+formatte)

      .then((response) => {
             if (!response.ok) {
               throw new Error("Failed to fetch visitor.");
             }
             return response.json();
           })
           .then((visitor1) => {
               visitor1.forEach((visitor) => {
                 let date1 = new Date(visitor.started_at);

                 let formattedDate1 = date1.getFullYear() + "-" +
                     ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                     ("0" + date1.getDate()).slice(-2) + " " +
                     ("0" + date1.getHours()).slice(-2) + ":" +
                     ("0" + date1.getMinutes()).slice(-2) + ":" +
                     ("0" + date1.getSeconds()).slice(-2);

                     let date2 = new Date(visitor.ended_at);

                     let formattedDate2 = date2.getFullYear() + "-" +
                         ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                         ("0" + date2.getDate()).slice(-2) + " " +
                         ("0" + date2.getHours()).slice(-2) + ":" +
                         ("0" + date2.getMinutes()).slice(-2) + ":" +
                         ("0" + date2.getSeconds()).slice(-2);
                 var row =
                   "<tr>" +
                   "<td>" +
                   visitor.event +
                   "</td>" +
                   "<td>" +
                   (formattedDate1) +
                   "</td>" +
                       "<td>" +
                             (formattedDate2) +
                             "</td>" +
                                 "<td>" +
                                       (visitor.status) +
                                       "</td>" +
                                           "<td>" +
                                                 (visitor.comment) +
                                                 "</td>" +
                                                     "<td>" +
                                                           (visitor.mark) +
                                                           "</td>"

                   "</tr>";
                 tableBody.insertAdjacentHTML("beforeend", row);
                  var buttonCell = document.createElement('td');
                         var button = document.createElement('button');
                         button.textContent = 'Обновить';

                         // Передаем параметры в функцию updatevisitor
                         button.onclick = function() { updatevisitor(visitor.id, d); };

                         buttonCell.appendChild(button);
                         tableBody.lastChild.appendChild(buttonCell);
               });
             })
              .catch((error) => {
                             console.error(
                               "Не удалось получить подробную информацию о посещении мероприятия:",
                               error
                             );
                           });
                           break;
              }
                 case "twodays":{
                        document.getElementById('Vis').textContent="Ближайшие мероприятия";
        let endDate = new Date();
        endDate.setDate(now.getDate() + 3);
   let formatte = endDate.getFullYear() + "-" +
                  ("0" + (endDate.getMonth() + 1)).slice(-2) + "-" +
                  ("0" + endDate.getDate()).slice(-2) + " " +
                  ("0" + endDate.getHours()).slice(-2) + ":" +
                  ("0" + endDate.getMinutes()).slice(-2) + ":" +
                  ("0" + endDate.getSeconds()).slice(-2);
      fetch("http://localhost:9090/getDate/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+emai+"/"+formatte)
     .then((response) => {
            if (!response.ok) {
              throw new Error("Failed to fetch visitor.");
            }
            return response.json();
          })
          .then((visitor1) => {
              visitor1.forEach((visitor) => {
                let date1 = new Date(visitor.started_at);

                let formattedDate1 = date1.getFullYear() + "-" +
                    ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                    ("0" + date1.getDate()).slice(-2) + " " +
                    ("0" + date1.getHours()).slice(-2) + ":" +
                    ("0" + date1.getMinutes()).slice(-2) + ":" +
                    ("0" + date1.getSeconds()).slice(-2);

                    let date2 = new Date(visitor.ended_at);

                    let formattedDate2 = date2.getFullYear() + "-" +
                        ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                        ("0" + date2.getDate()).slice(-2) + " " +
                        ("0" + date2.getHours()).slice(-2) + ":" +
                        ("0" + date2.getMinutes()).slice(-2) + ":" +
                        ("0" + date2.getSeconds()).slice(-2);
                var row =
                  "<tr>" +
                  "<td>" +
                  visitor.event +
                  "</td>" +
                  "<td>" +
                  (formattedDate1) +
                  "</td>" +
                      "<td>" +
                            (formattedDate2) +
                            "</td>" +
                                "<td>" +
                                      (visitor.status) +
                                      "</td>" +
                                          "<td>" +
                                                (visitor.comment) +
                                                "</td>" +
                                                    "<td>" +
                                                          (visitor.mark) +
                                                          "</td>"

                  "</tr>";
                tableBody.insertAdjacentHTML("beforeend", row);
                 var buttonCell = document.createElement('td');
                        var button = document.createElement('button');
                        button.textContent = 'Обновить';

                        // Передаем параметры в функцию updatevisitor
                        button.onclick = function() { updatevisitor(visitor.id, d); };

                        buttonCell.appendChild(button);
                        tableBody.lastChild.appendChild(buttonCell);
              });
            })
             .catch((error) => {
                            console.error(
                              "Не удалось получить подробную информацию о посещении мероприятия:",
                              error
                            );
                          });
                          break;
                            }
                               case "tomorrow":{
                                      document.getElementById('Vis').textContent="Ближайшие мероприятия";
                                   let endDate = new Date();
                                   endDate.setDate(now.getDate() + 2);
                                 let formatte = endDate.getFullYear() + "-" +
                                                ("0" + (endDate.getMonth() + 1)).slice(-2) + "-" +
                                                ("0" + endDate.getDate()).slice(-2) + " " +
                                                ("0" + endDate.getHours()).slice(-2) + ":" +
                                                ("0" + endDate.getMinutes()).slice(-2) + ":" +
                                                ("0" + endDate.getSeconds()).slice(-2);
                                    fetch("http://localhost:9090/getDate/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+emai+"/"+formatte)
                                     .then((response) => {
                                            if (!response.ok) {
                                              throw new Error("Failed to fetch visitor.");
                                            }
                                            return response.json();
                                          })
                                          .then((visitor1) => {
                                              visitor1.forEach((visitor) => {
                                                let date1 = new Date(visitor.started_at);

                                                let formattedDate1 = date1.getFullYear() + "-" +
                                                    ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                                                    ("0" + date1.getDate()).slice(-2) + " " +
                                                    ("0" + date1.getHours()).slice(-2) + ":" +
                                                    ("0" + date1.getMinutes()).slice(-2) + ":" +
                                                    ("0" + date1.getSeconds()).slice(-2);

                                                    let date2 = new Date(visitor.ended_at);

                                                    let formattedDate2 = date2.getFullYear() + "-" +
                                                        ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                                                        ("0" + date2.getDate()).slice(-2) + " " +
                                                        ("0" + date2.getHours()).slice(-2) + ":" +
                                                        ("0" + date2.getMinutes()).slice(-2) + ":" +
                                                        ("0" + date2.getSeconds()).slice(-2);
                                                var row =
                                                  "<tr>" +
                                                  "<td>" +
                                                  visitor.event +
                                                  "</td>" +
                                                  "<td>" +
                                                  (formattedDate1) +
                                                  "</td>" +
                                                      "<td>" +
                                                            (formattedDate2) +
                                                            "</td>" +
                                                                "<td>" +
                                                                      (visitor.status) +
                                                                      "</td>" +
                                                                          "<td>" +
                                                                                (visitor.comment) +
                                                                                "</td>" +
                                                                                    "<td>" +
                                                                                          (visitor.mark) +
                                                                                          "</td>"

                                                  "</tr>";
                                                tableBody.insertAdjacentHTML("beforeend", row);
                                                 var buttonCell = document.createElement('td');
                                                        var button = document.createElement('button');
                                                        button.textContent = 'Обновить';

                                                        // Передаем параметры в функцию updatevisitor
                                                        button.onclick = function() { updatevisitor(visitor.id, d); };

                                                        buttonCell.appendChild(button);
                                                        tableBody.lastChild.appendChild(buttonCell);
                                              });
                                            })
                                             .catch((error) => {
                                                            console.error(
                                                              "Не удалось получить подробную информацию о посещении мероприятия:",
                                                              error
                                                            );
                                                          });
                                                          break;
                                          }
              }
    }




  // Функция для обновления таблицы с информацией о посещении мероприятиям
  function updatevisitorTable(cd,d,emai) {
      var tableBody = document.querySelector("#visitorTable tbody");
      tableBody.innerHTML = ""; // Очищаем таблицу перед обновлением

           console.log(emai)+" /";
      fetch("http://localhost:9090/get/emailtrue/"+emai+"/true")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch visitor.");
        }
        return response.json();
      })
      .then((visitor1) => {

     // root1.textContent = visitorData.email;
         fetch("http://localhost:9090/get/id/"+d)
        .then((response) => {
          if (!response.ok) {
            throw new Error("Failed to fetch visitor.");
          }
          return response.json();
        })
        .then((visitor) => {
          visitor.forEach((visitor) => {
            let date1 = new Date(visitor.started_at);

            let formattedDate1 = date1.getFullYear() + "-" +
                ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                ("0" + date1.getDate()).slice(-2) + " " +
                ("0" + date1.getHours()).slice(-2) + ":" +
                ("0" + date1.getMinutes()).slice(-2) + ":" +
                ("0" + date1.getSeconds()).slice(-2);

                let date2 = new Date(visitor.ended_at);

                let formattedDate2 = date2.getFullYear() + "-" +
                    ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                    ("0" + date2.getDate()).slice(-2) + " " +
                    ("0" + date2.getHours()).slice(-2) + ":" +
                    ("0" + date2.getMinutes()).slice(-2) + ":" +
                    ("0" + date2.getSeconds()).slice(-2);
            var row =
              "<tr>" +
              "<td>" +
              visitor.event +
              "</td>" +
              "<td>" +
              (formattedDate1) +
              "</td>" +
                  "<td>" +
                        (formattedDate2) +
                        "</td>" +
                            "<td>" +
                                  (visitor.status) +
                                  "</td>" +
                                      "<td>" +
                                            (visitor.comment) +
                                            "</td>" +
                                                "<td>" +
                                                      (visitor.mark) +
                                                      "</td>"

              "</tr>";
            tableBody.insertAdjacentHTML("beforeend", row);
             var buttonCell = document.createElement('td');
                    var button = document.createElement('button');
                    button.textContent = 'Обновить';

                    // Передаем параметры в функцию updatevisitor
                    button.onclick = function() { updatevisitor(visitor.id, d); };

                    buttonCell.appendChild(button);
                    tableBody.lastChild.appendChild(buttonCell);
          });
        })
        .catch((error) => {
          console.error("Failed to fetch visitor:", error);
        });
          }
              )
              .catch((error) => {
                console.error(
                  "Не удалось получить подробную информацию о посещении мероприятия:",
                  error
                );
              });
    }

    // Функция для обновления информации о посещении мероприятия
    function updatevisitor(id, id_visit) {
      // Получаем изначальные значения информации о посещении мероприятия
      fetch("http://localhost:9090/get/uuidid/"+id_visit+"/"+ id)
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Не удалось получить подробную информацию о посещении мероприятия."
            );
          }
          return response.json();
        })
        .then((visitor) => {
          /** @type { (info: { root: HTMLElement }) => void } */
         const setContent = function({root, menu}) {
          id1=visitor[0].id;
          id2=visitor[0].id_visit;
    var options = selType.getElementsByTagName("option");

    // выполняем тернарную операцию для каждой опции
    for (var i = 0; i < options.length; i++) {
      options[i].selected = (options[i].innerHTML === visitor[0].status) ? true : false;
    }
      root.innerHTML =`<div class="overlay" id="overlay">
            <div class="popup" id="popup">
                <label for="commentField">Комментарий:</label>
                <input type="text" placeholder="Комментарий" textContent=$"{visitor[0].comment}" id="commentField"/>
                <label for="markField">Оценка:</label>
                <input type="number" placeholder="5" textContent=$"{visitor[0].mark}" id="markField" step="1" min="1" max="5"/>

                 <button id="changeBtn" onclick="changeData(id1, id2)">Изменить</button>
                       <button id="exit" onclick="exitBtn()">Отмена</button>
            </div>
        </div>`;
        overlay = document.getElementById('overlay');
            popup = document.getElementById('popup');
        overlay.style.display = 'block';
popup.appendChild(newLabel);
popup.appendChild(selType);

var currentTime = new Date();
currentTime.setHours(currentTime.getHours() + 3);
var currentTimeMoscow =currentTime.toISOString().slice(0, 19).replace('T', ' ');
if(new Date(currentTimeMoscow)<new Date(visitor[0].started_at)){document.getElementById('changeBtn').disabled = true;}
        }
        setContent({
          root: document.getElementById("update-content")
        })
      }  )
        .catch((error) => {
          console.error(
            "Не удалось получить подробную информацию о посещении мероприятия:",
            error
          );
        });
    }
    function exitBtn()
    {
            document.getElementById('overlay').style.display = 'none';}
    var overlay ="";
    var selType=document.createElement('select');

    var newLabel=document.createElement('label');
    newLabel.innerHTML='<label id="typeLabel">Статус:</label>';
    selType.innerHTML='<select id="type" name="type"> </select>';
     var option1 = document.createElement('option');
                             option1.textContent = "Не отмечено";
      var optionValue1 = '0';
      option1.value=optionValue;

                             selType.appendChild(option1);
                               var option2 = document.createElement('option');
                                                          option2.textContent = "Присутствовал";
                                   var optionValue = '1';
                                   option2.value=optionValue;

                                                          selType.appendChild(option2);
                             selType.options[1].selected=true;



    function changeData(id,id_visit) {

    var newStatus = selType.options[selType.selectedIndex].textContent;
    var newComment=commentField.value;
    var newMark = Number(markField.value);
      fetch("http://localhost:9090/get/uuidid/" + id_visit+"/"+id)
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Не удалось получить подробную информацию о посещении мероприятия."
            );
          }
          return response.json();
        })
        .then((visitor) => {

                  fetch("http://localhost:9090/" + id +"/change", {
                    method: "POST",
                    headers: {
                      "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                      status: newStatus,
                      comment: newComment,
                      mark: newMark,
                    }),
                  })
                    .then((response) => {
                      if (!response.ok) {
                        throw new Error("Не удалось обновить информацию о посещении мероприятия.");
                      }
                      updatevisitorTable(visitor[0].surname+" "+visitor[0].name+" "+visitor[0].patronymic, visitor[0].id_visit, visitor[0].email);
                      updatevisitorTable2(visitor[0].surname+" "+visitor[0].name+" "+visitor[0].patronymic, visitor[0].id_visit, visitor[0].email,selectDate.options[selectDate.selectedIndex].value);
                    })
                    .catch((error) => {
                      console.error("Не удалось обновить информацию о посещении мероприятия:", error);
                    });

        })
        document.getElementById('overlay').style.display = 'none';
        alert("Данные обновлены");
        }
           var sur='Иванов';
            var name='Иван';
            var patr='Иванович';
            var email1 ='exedy145@mail.ru';
var rooter=document.createElement('div');
rooter.innerHTML='<div style=" position: fixed;  top: 0;   right: 0;   z-index: 9999;" id="er"></div>';
document.body.appendChild(rooter);
  var roof=document.createElement('select');

          roof.innerHTML ='<select id="visitorLabel"></select>';
                         var option = document.createElement('option');
                         option.textContent = sur + " " + name + " " + patr;
  var optionValue = '550e8400-e29b-41d4-a716-446655440000';
  option.value=optionValue;

                         roof.appendChild(option);
                         roof.options[0].selected=true;

                    var root1=document.createElement('label');

                                 root1.innerHTML ='<label id="emailLabel" textContent="email1"></label>';


     document.getElementById('selectDate').addEventListener('change', function() {

     let cd=roof.options[roof.selectedIndex].textContent.split(" ");
     let d=roof.options[roof.selectedIndex].value;

     fetch("http://localhost:9090/getFIOUUID/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+d)
                 .then((response) => {
         if (!response.ok) {
                              throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
                              console.log("The number of str is "+ new Error().lineNumber);
                            }
                            return response.json();
                          })
                          .then((visitor1) => {
                   const emai = visitor1[0].email;

                                      updatevisitorTable2(cd,d,emai,selectDate.options[selectDate.selectedIndex].value);
root1.textContent=emai;
                   }
                   )
            .catch((error) => {
                                       console.error(
                                         "Не удалось получить подробную информацию о посещении мероприятия:",
                                         error
                                       );
                                     });


                             });




     roof.addEventListener('change', function() {

     let cd=roof.options[roof.selectedIndex].textContent.split(" ");
     let d=roof.options[roof.selectedIndex].value;

     fetch("http://localhost:9090/getFIOUUID/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+d)
                 .then((response) => {
         if (!response.ok) {
                              throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
                              console.log("The number of str is "+ new Error().lineNumber);
                            }
                            return response.json();
                          })
                          .then((visitor1) => {
                   const emai = visitor1[0].email;
                                  updatevisitorTable(cd,d,emai);
                                      updatevisitorTable2(cd,d,emai,selectDate.options[selectDate.selectedIndex].value);
                              console.log(emai);
root1.textContent=emai;
                                fetch("http://localhost:9090/get/currentevent/"+visitor1[0].id_visit)
                                        .then((response) => {
                                          if (!response.ok) {
                                            throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
                                            console.log("The number of str is "+ new Error().lineNumber);
                                          }
                                          return response.json();
                                        })
                                        .then((visitor) => {
                                let date1 = new Date(visitor[0].started_at);

                                let formattedDate1 = date1.getFullYear() + "-" +
                                    ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
                                    ("0" + date1.getDate()).slice(-2) + " " +
                                    ("0" + date1.getHours()).slice(-2) + ":" +
                                    ("0" + date1.getMinutes()).slice(-2) + ":" +
                                    ("0" + date1.getSeconds()).slice(-2);

                                    let date2 = new Date(visitor[0].ended_at);

                                    let formattedDate2 = date2.getFullYear() + "-" +
                                        ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
                                        ("0" + date2.getDate()).slice(-2) + " " +
                                        ("0" + date2.getHours()).slice(-2) + ":" +
                                        ("0" + date2.getMinutes()).slice(-2) + ":" +
                                        ("0" + date2.getSeconds()).slice(-2);
                                        id1=visitor[0].id;
                                          ema=visitor1[0].email;
                                        id2=visitor[0].id_visit;
                                        if(visitor[0].status=='Присутствовал'){}
                                              else{
                                     var newDiv=document.createElement('div');
                                  newDiv.innerHTML =`<div class="overlay" id="overlay2">
                                                  <div class="popup" id="popup2">
                                                  <h2>Информация!</h2>
                                                  <p>В данный момент с ${formattedDate1} по ${formattedDate2} проходит мероприятие
                                                  ${visitor[0].event}, на которое Вы были зарегистрированы<p>
                                                    <button id="closeBtn" onclick="closeData(id1,id2,ema,false)">Я здесь</button>
                                                         </div>
                                                        </div>`;
                                                            document.body.appendChild(newDiv);
                                                     overlay2 = document.getElementById('overlay2');
                                                        overlay2.style.display = 'block';

                                                 }     })
                                                      .catch((error) => {
                                                             console.error(
                                                               "Не удалось получить подробную информацию опосещении мероприятия:",
                                                               error
                                                             );
                                                           });

                   }
                   )
            .catch((error) => {
                                       console.error(
                                         "Не удалось получить подробную информацию опосещении мероприятия:",
                                         error
                                       );
                                     });


                             });

    // Обновляем таблицу с информацию о посещении мероприятиям при загрузке страницы
    document.addEventListener("DOMContentLoaded", function () {
    on_load();
let cd=roof.options[roof.selectedIndex].textContent.split(" ");
     let d=roof.options[roof.selectedIndex].value;
     fetch("http://localhost:9090/getFIOUUID/"+cd[0]+"/"+cd[1]+"/"+cd[2]+"/"+d)
            .then((response) => {
                       if (!response.ok) {
                         throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
                         console.log("The number of str is "+ new Error().lineNumber);
                       }
                       return response.json();
                     })
                  .then((visitor1) => {
           const emai = visitor1[0].email;
           console.log(emai);
               updatevisitorTable(cd,d,emai);
                   updatevisitorTable2(cd,d,emai,selectDate.options[selectDate.selectedIndex].value);
           }
           )
    .catch((error) => {
                               console.error(
                                 "Не удалось получить подробную информацию опосещении мероприятия:",
                                 error
                               );
                             });

                         }    );

    var id1="";
    var id2="";
     var overlay1="";


    function on_load(){
    rooter.appendChild(roof);

         rooter.appendChild(root1);
         root1.textContent=email1;
     fetch("http://localhost:9090/getFIO/"+sur+"/"+name+"/"+patr+"/"+email1)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch visitor.");
        }
        return response.json();
      })
      .then((visitor) => {
      console.log(visitor[0].id_visit, visitor[0].surname, visitor[0].name);
       var xd = visitor[0].id_visit;
          fetch("http://localhost:9090/get/currentevent/"+xd)
          .then((response) => {
            if (!response.ok) {
              throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
              console.log("The number of str is "+ new Error().lineNumber);
            }
            return response.json();
          })
          .then((visitor1) => {
  let date1 = new Date(visitor1[0].started_at);

  let formattedDate1 = date1.getFullYear() + "-" +
      ("0" + (date1.getMonth() + 1)).slice(-2) + "-" +
      ("0" + date1.getDate()).slice(-2) + " " +
      ("0" + date1.getHours()).slice(-2) + ":" +
      ("0" + date1.getMinutes()).slice(-2) + ":" +
      ("0" + date1.getSeconds()).slice(-2);

      let date2 = new Date(visitor1[0].ended_at);

      let formattedDate2 = date2.getFullYear() + "-" +
          ("0" + (date2.getMonth() + 1)).slice(-2) + "-" +
          ("0" + date2.getDate()).slice(-2) + " " +
          ("0" + date2.getHours()).slice(-2) + ":" +
          ("0" + date2.getMinutes()).slice(-2) + ":" +
          ("0" + date2.getSeconds()).slice(-2);
          id1=visitor1[0].id;
                    ema=visitor1[0].email;
          id2=visitor1[0].id_visit;
          if(visitor1[0].status=='Присутствовал'){}
                else{
       var newDiv=document.createElement('div');
    newDiv.innerHTML =`<div class="overlay" id="overlay1">
                    <div class="popup" id="popup1">
                    <h2>Информация!</h2>
                    <p>В данный момент с ${formattedDate1} по ${formattedDate2} проходит мероприятие
                    ${visitor1[0].event}, на которое Вы были зарегистрированы<p>
                      <button id="closeBtn" onclick="closeData(id1,id2,ema, true)">Я здесь</button>
                           </div>
                          </div>`;
                              document.body.appendChild(newDiv);
                       overlay1 = document.getElementById('overlay1');
                          overlay1.style.display = 'block';

                   }     })
                        .catch((error) => {
                               console.error(
                                 "Не удалось получить подробную информацию опосещении мероприятия:",
                                 error
                               );
                             });
      })
       .catch((error) => {
              console.error(
                "Не удалось получить подробную информацию опосещении мероприятия:",
                error
              );
                     console.log("The number of str is "+ new Error().lineNumber);
            });
            fetch("http://localhost:9090/all")
           .then((response) => {
                      if (!response.ok) {
                        throw new Error("Failed to fetch visitor. The number of str is "+ new Error().lineNumber);
                        console.log("The number of str is "+ new Error().lineNumber);
                      }
                      return response.json();
                    })
                    .then((visitor1) => {


                     function addOptions(options, optionValue) {
                                var option = document.createElement('option');
                                 option.text = options;
option.value=optionValue;
                        if (!roof.querySelector(`option[value="${optionValue}"]`)) {
                            roof.appendChild(option);
                          }
//

                             }
                     visitor1.forEach((visitor2) => {
addOptions(visitor2.surname+" "+visitor2.name+" "+ visitor2.patronymic, visitor2.id_visit);
                    }
                    )

                    }
                    )

                                            .catch((error) => {
                                                   console.error(
                                                     "Не удалось получить подробную информацию опосещении мероприятия:",
                                                     error
                                                   );
                                                 });


    }



