function updateHistory() {
  actionHistoryElement = $('#actionsHistory');
  // update history
  $.ajax({
    url : $("#showHistoryAjaxUrl").val(),
    success : function(data) {
      actionHistoryElement.html(data);
      // highlight history
      actionHistoryElement.effect("highlight", {}, 2000);
    }
  });
}