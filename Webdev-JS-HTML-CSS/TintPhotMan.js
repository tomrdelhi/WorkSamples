height = 320;
width = 426;
VCheck = true;
SCheck = true;

function hardWareCheck() {
        var boola = !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
        var butta = document.getElementById("HCheck");
        if (boola){
          var Button1 = document.createElement("BUTTON");
          Button1.setAttribute("id", "tempRes");
          var outText = document.createTextNode("Good To Go!");
          Button1.appendChild(outText);
          butta.parentNode.replaceChild(Button1, butta);
        } else {
          var Button1 = document.createElement("BUTTON");
          Button1.setAttribute("id", "tempRes");
          var outText = document.createTextNode("There's an Issue");
          Button1.appendChild(outText);
          butta.parentNode.replaceChild(Button1, butta);
        }
      }


function VStart(){
      if (document.getElementById("SubBut")){
      document.getElementById("SubBut").style.display = "none";}
      if (document.getElementById("HCheck")){
        temp1 = document.getElementById("HCheck");
        temp1.parentNode.removeChild(temp1);
      }
      if (document.getElementById("tempRes")){
        temp2 = document.getElementById("tempRes");
        temp2.parentNode.removeChild(temp2);
      }
      var video = document.getElementById('video');
      video.style.display = "block";
    if(VCheck){
        navigator.getUserMedia = ( navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
        var errorCallback = function(e) {
    console.log('Reeeejected!', e);
    };
    navigator.getUserMedia({video: true, audio: false}, function(localMediaStream) {

    video.src = window.URL.createObjectURL(localMediaStream);
      video.onloadedmetadata = function(e) {
      };
      }, errorCallback);
    VCheck = false;
  }
    else{
      video.hidden = false;
      document.getElementById("photo").style.display = "none";
      document.getElementById("photo").hidden = true;
    }
    video.setAttribute("height", height);
    video.setAttribute("width", width);
    if (document.getElementById("VidStart")){
      oldBut = document.getElementById("VidStart");}
      else{
        oldBut = document.getElementById("RBut")
      }
      newBut = document.createElement("BUTTON");
      newButText = document.createTextNode("Take Picture");
      newBut.appendChild(newButText);
      newBut.setAttribute("id", "PhotoBut");
      newBut.setAttribute("onclick", "TakePhot()");
      oldBut.parentNode.replaceChild(newBut, oldBut);
      }
      


function TakePhot(){
          var video = document.getElementById('video');
          var canvas = document.getElementById("canvas");
         
          
          var replaceBut = document.createElement("BUTTON");
          replaceBut.setAttribute("id", "RBut");
          replaceBut.addEventListener("click", this.VStart)
          var replaceButText = document.createTextNode("New Picture");
          replaceBut.appendChild(replaceButText);
          var oldBut = document.getElementById("PhotoBut");
          oldBut.parentNode.replaceChild(replaceBut, oldBut);
          
          canvas.height = video.height;
          canvas.width = video.width;
          
          var context = canvas.getContext('2d');
          var video = document.getElementById("video");
          var photo = document.getElementById("photo") 
          context.drawImage(video, 0, 0, width, height);

          var data = canvas.toDataURL('image/png');
          photo.setAttribute('src', data);
          photo.hidden = false;
          photo.style.display = "block";

          video.setAttribute("hidden", true);
          video.style.display = "none";
         
          if (SCheck){
          var submitBut = document.createElement("BUTTON");
          var submitButText = document.createTextNode("Submit");
          submitBut.appendChild(submitButText);
          submitBut.setAttribute("id", "SubBut");
          submitBut.addEventListener("click", this.loadAnswers);
          wrapper = document.getElementById("whopper");
          wrapper.appendChild(submitBut);
        }
        else{
          document.getElementById("SubBut").style.display = "block";
        }
        }

function loadAnswers(){
  var photo = document.getElementById("photo");
  window.location.assign("Answers.html");
}

function ALFunc(){

  var colors = ['#FFD8B3','#FFBF80','#FFCE99', '#FFCE80', '#FFB366'];
  var cosmetics = ["Chanel No. 10", "Chanel No. 20","Chanel No. 21",
  "Chanel No. 30","Chanel No. 12 Rose","Chanel No. 22 Rose"];
  var color = colors[Math.floor(Math.random()*colors.length)];
  var txtcolor = document.createTextNode(color);
  document.getElementById("colorVal").appendChild(txtcolor);
  document.getElementById("circle").style.background = color;

  var cosSelection = [0, 1, 2, 4, 5];
  cosSelection = shuffleArray(cosSelection);
  var c1 = document.createTextNode(cosmetics[cosSelection[0]]);
  document.getElementById("p1").appendChild(c1);
  var c2 = document.createTextNode(cosmetics[cosSelection[1]]);
  document.getElementById("p2").appendChild(c2);
  var c3 = document.createTextNode(cosmetics[cosSelection[2]]);
  document.getElementById("p3").appendChild(c3);
  var c4 = document.createTextNode(cosmetics[cosSelection[3]]);
  document.getElementById("p4").appendChild(c4);
  var c5 = document.createTextNode(cosmetics[cosSelection[4]]);
  document.getElementById("p5").appendChild(c5);

}

function shuffleArray(array) {
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    return array;}
