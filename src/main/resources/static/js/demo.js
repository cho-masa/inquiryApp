$(function(){
  // 都道府県プルダウン変更時に市区町村プルダウンを再作成
  $('#pref').change(function() {
      $.ajax({
        url: "/api/cities",
        data: {"prefCd" : $(this).val()}
      }).done(function(data){
        $("#city").html("");
        $("#city").append("<option value=''>---</option>");
        for (var i = 0; i < data.length; i++) {
            $("#city").append("<option value=" + data[i].cd + ">" + data[i].name + "</option>");
        }
      }).fail(function(data){
        alert("error! ");
      });
  })
});
