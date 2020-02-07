<#import "parts/common.ftlh" as c>
<#import "parts/addPlayerForm.ftlh" as pf>

<@c.page>
<#if newPlayers??>
    <#list newPlayers as player>
        <div class="alert alert-success" role="alert">
            Игроков <b>${player.surname} ${player.name}</b> успешно зарегистрирован
        </div>
    </#list>
</#if>
<h2>Список участников:</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Фамилия</th>
      <th scope="col">Имя</th>
      <th scope="col">Отчество</th>
      <th scope="col">Школа</th>
      <th scope="col">Эло</th>
      <th scope="col">Удалить</th>
    </tr>
  </thead>
  <tbody>
    <#list players as player>
        <tr>
          <th scope="row">${player?index}</th>
          <td>${player.surname}</td>
          <td>${player.name}</td>
          <td>${player.lastname}</td>
          <td>${player.school.name}</td>
          <td>${player.elo}</td>
          <td>
              <a href="/delplayer/${player.id}">
                <img src="/del.png" class="rounded" alt="Исключить учасника">
              </a>
          </td>
        </tr>
    <#else>
        В данный момент нет зарегистрированных участников
    </#list>
</@c.page>

