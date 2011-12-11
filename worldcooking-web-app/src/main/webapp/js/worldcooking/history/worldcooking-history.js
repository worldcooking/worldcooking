function updateHistory() {
  actionHistoryElement = $('#actionsHistory');
  // update history
  // TODO retrieve URL from JSP hidden field (or form action)
  $.ajax({
    url : '../direct/history',
    success : function(data) {
      actionHistoryElement.html(data);
      actionHistoryElement.effect("highlight", {}, 2000);
    }
  });
  // highlight history
}