$.extend($.fn.datagrid.methods, {
            keyCtr : function (jq) {
                return jq.each(function () {
                    var grid = $(this);
                    grid.datagrid('getPanel').panel('panel').attr('tabindex', 1).bind('keydown', function (e) {
                        switch (e.keyCode) {
                        case 38: // up
                            var selected = grid.datagrid('getSelected');
                            if (selected) {
                                var index = grid.datagrid('getRowIndex', selected);
                                grid.datagrid('selectRow', index - 1);
                                onClickCell(index - 1,currentField);
                            } else {
                                var rows = grid.datagrid('getRows');
                                grid.datagrid('selectRow', rows.length - 1);
                            }
                            break;
                        case 40: // down
                            var selected = grid.datagrid('getSelected');
                            if (selected) {
                                var index = grid.datagrid('getRowIndex', selected);
                                grid.datagrid('selectRow', index + 1);
                                onClickCell(index + 1,currentField);
                            } else {
                                grid.datagrid('selectRow', 0);
                            }
                            break;
                        }
                    });
                });
            }
});