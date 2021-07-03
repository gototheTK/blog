let index = {
  init: function () {
    const save = document.getElementById("btn-save");
    if (save != null) {
      save.addEventListener("click", () => {
        let temp = new request("save");

        temp.post();
      });
    }

    const erase = document.getElementById("btn-delete");
    if (erase != null) {
      erase.addEventListener("click", () => {
        let temp = new request("delete");

        temp.delete();
      });
    }

    const update = document.getElementById("btn-update");
    if (update != null) {
      update.addEventListener("click", () => {
        let temp = new request("update");
        temp.update();
      });
    }
  },
};

index.init();

class request {
  constructor(url) {
    switch (url) {
      case "save":
        this.data = {
          title: document.getElementById("title").value,
          content: document.getElementById("content").value,
        };
        break;
      case "delete":
        this.id = document.getElementById("id").textContent;
        break;
      case "update":
        this.data = {
          id: document.getElementById("id").textContent,
          title: document.getElementById("title").value,
          content: document.getElementById("content").value,
        };
        break;
      default:
        alert("잘못된 명령입니다.");
    }
    this.url = "/api/board/" + url;
  }

  post() {
    fetch(this.url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        charset: "utf-8",
      }, //body데이터가 어떤 타입인지
      dataType: "json", // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
      body: JSON.stringify(this.data),
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        if (data.status === 200) {
          alert("글쓰기가 완료되었습니다.");
          location.href = "/";
        } else {
          alert("fail");
        }
      })
      .catch((error) => {
        console.log("Error", error);
      });
  }

  delete() {
    console.log("삭제하기");
    fetch(this.url + "/" + this.id, {
      method: "delete",
      dataType: "json",
    })
      .then((response) => {
        if (response.status === 200) {
          alert("삭제가 완료되었습니다.");
          location.href = "/";
        }
      })
      .catch((error) => {
        console.log("Error", error);
      });
  }

  update() {
    fetch(this.url + "/" + this.data.id, {
      method: "put",
      headers: {
        "Content-Type": "application/json",
        charset: "utf-8",
      }, //body데이터가 어떤 타입인지
      dataType: "json", // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
      body: JSON.stringify(this.data),
    })
      .then((response) => {
        return response.json();
      })
      .then((response) => {
        if (response.status === 200) {
          alert("수정이 완료되었습니다.");
          location.href = "/board/" + this.data.id;
        }
      });
  }
}
