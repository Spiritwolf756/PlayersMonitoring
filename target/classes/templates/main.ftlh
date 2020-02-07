<#import "parts/common.ftlh" as c>
<@c.page>

    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="text" name="tag" placeholder="Тэг" />

            <button type="submit">Добавить</button>
        </form>
    </div>



    <div>Список сообщений</div>
    <form method="post" action="filter">
        <input type="text" name="filter">

        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
    </div>
    <#else>
    No message
    </#list>


</@c.page>
