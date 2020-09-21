$(document).ready(function(){
    var categories = new Categories();
    categories.load(function(){
        var detail = new Detail(categories);
        var list = new List(categories, detail);


        // ugly code
        detail.saveDone = function(task){
            list.load();
        };

        list.load();
    });
});
function Categories(){
    var self = this;
    this.data = [];
    this.load = function(callback){
            $.ajax({
                url : "/task/categories",
                type : 'GET',
                dataType: "json",
                success : function(result) {
                    self.data = result;
                    callback.call();
                }
            });
        };

    this.get = function(id){
        for(j = 0, len = self.data.length; j < len; j++) {
            if(id === self.data[j].id){
                return self.data[j].name;
            }
        }
        return "未知";
    }
}
function Detail(categories){
    var self = this;

    this.id = 0;
    this.showDetail = $('.showDetail');
    this.newTask = $('.newTask');
    this.newBtn = $('.btn-new');
    this.saveBtn = $('.btn-save');
    this.delBtn = $('.btn-del');

    this.completeBtn = $('.btn-complete');
    this.unCompleteBtn = $('.btu-unComplete');

    this.title = $('#taskTitle');
    this.content = $('#taskContent');
    this.taskCategory = $('#taskCategory');

    this.success = $('.alert-success');
    this.alert = $('.alert-danger');

    this.saveDone = function(){};

    this.renderCategory = function(id){
        self.taskCategory.children().each(function(){
            if($(this).val() == id){
                $(this).attr("selected","selected");
            } else {
                $(this).removeAttr("selected");
            }
        });
    };

    for(var i=0;i<categories.data.length;i++){
        var category = categories.data[i];
        var option = '<option value="'+category.id+'">'+category.name+'</option>'
        this.taskCategory.append(option);
    }

    this.renderDetail = function(data){
        self.delBtn.show();
        self.success.hide();
        self.alert.hide();

        self.id = data.id;

        self.showDetail.find('.panel-title').html("修改任务");

        self.title.val(data.title);
        self.content.val(data.content);

        if(data.status == 'completed'){
            self.completeBtn.hide();
            self.unCompleteBtn.show();
        } else {
            self.completeBtn.show();
            self.unCompleteBtn.hide();
        }
        self.renderCategory(data.categoryId);
        self.saveBtn.text('更新');
    };

    this.newBtn.on('click', function(){
        self.delBtn.hide();
        self.completeBtn.hide();
        self.unCompleteBtn.hide();
        self.success.hide();
        self.alert.hide();

        self.id = 0;
        self.renderCategory(0);

        self.showDetail.find('.panel-title').html("新建任务");

        self.title.val('');
        self.content.val('');
        self.saveBtn.text('保存');
    });
    this.saveBtn.on('click', function(){
        if(isEmpty(self.title.val())
            || isEmpty(self.content.val())
            || isEmpty(self.taskCategory.val())){
            self.alert.text('请填写完整信息').show();
            return;
        }
        $.ajax({
            url : "/task/save",
            type : 'POST',
            dataType: "json",
            data : {
                id: self.id,
                title: $('#taskTitle').val(),
                content: $('#taskContent').val(),
                category: self.taskCategory.val()
            },
            success : function(result) {
                self.saveDone(result);
                self.success.text('操作成功').show();
                self.alert.hide();
            }
        });
    });
    this.delBtn.on('click', function(){
        $.ajax({
            url : "/task/del",
            type : 'POST',
            dataType: "json",
            data : {
                id: self.id
            },
            success : function(result) {
                self.saveDone(result);
                self.success.text('删除成功').show();
                self.alert.hide();
            }
        });
    });
    this.completeBtn.on('click',function(){
        $.ajax({
            url : "/task/complete",
            type : 'POST',
            data : {
                id: self.id
            },
            success : function(result) {
                $('tr[data-id='+self.id+']').addClass('completed');
                self.completeBtn.hide();
                self.unCompleteBtn.show();
                self.success.text('操作成功').show();
                self.alert.hide();
            }
        });
    });

    this.unCompleteBtn.on('click',function(){
        $.ajax({
            url : "/task/unComplete",
            type : 'POST',
            data : {
                id: self.id
            },
            success : function(result) {
                $('tr[data-id='+self.id+']').removeClass('completed');
                self.completeBtn.show();
                self.unCompleteBtn.hide();
                self.success.text('操作成功').show();
                self.alert.hide();
            }
        });
    });
}

function List(categories, detail){
    var self = this;

    this.detail = detail;
    this.curPage = 1;
    this.totalRecords = 1;
    this.datas = [];
    this.dom = $('.tasks');

    this.status = "all";
    $('ul.nav').children('li').on('click',function(tab){
        var selected = this;
        $(this).addClass('active');
        self.status = $(this).data('status');
        $('ul.nav').children('li').each(function(){
            if(this != selected){
                $(this).removeClass('active');
            }
        });
        self.load();
    });

    this.load = function(){
        $.ajax({
            url : "/task/list",
            type : 'POST',
            dataType: "json",
            data : {
                page: self.curPage,
                status: self.status
            },
            success : function(result) {
                self.totalRecords = result.totalRecords;
                self.datas = result.data;

                self.reRender();
            }
        });
    };
    this.reRender = function(){
        self.dom.empty();

        for(var i=0;i<self.datas.length;i++){
            var data = self.datas[i];
            self.renderLine(data);
        }
    };
    this.renderLine = function(data){
        var html = "<tr data-id='"+data.id+"'><td>"+data.id+"</td><td>"+categories.get(data.categoryId)+"</td><td>"+data.gmtCreate+"</td><td>"+data.title+"</td></tr>";
        var line = $(html);
        if(data.status == 'completed'){
            line.addClass('completed');
        }
        line.on('click', function(){
            detail.renderDetail(data);
        });
        self.dom.prepend(line);
    };
}

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}