let index = {
  init: function () {
    const join = document.getElementById("btn-join");
    if (join != null) {
      join.addEventListener("click", () => {
        let temp = new request("join");

        temp.post();
      });
    }
  },
};

index.init();

class request {
  constructor(url) {
    this.data = {
      username: document.getElementById("username").value,
      password: document.getElementById("password").value,
    };

    if (url === "join") {
      this.data.email = document.getElementById("email").value;
    }

    this.url = "/auth/" + url;
    console.log("호출했습니다." + this.url);
  }

  post() {
    //회원가입 수행 요청 (100초 가정)
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
          location.href = "/";
        } else {
          alert("fail");
        }
      })
      .catch((error) => {
        console.log("Error", error);
      });
  }

  put() {}
}
