<#import "parts/common.ftlh" as c>


<@c.page>

<h2>Список игроков с лучшей динамикой за последние 5 минут:</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Фамилия</th>
      <th scope="col">Имя</th>
      <th scope="col">Отчество</th>
      <th scope="col">Школа</th>
      <th scope="col">Эло</th>
      <th scope="col">Динамика</th>
    </tr>
  </thead>
  <tbody>
    <#list players as player, elo>
        <tr>
          <th scope="row">${player?index}</th>
          <td>${player.surname}</td>
          <td>${player.name}</td>
          <td>${player.lastname}</td>
          <td>${player.school.name}</td>
          <td>${player.elo}</td>
          <td>${elo}</td>
        </tr>
    <#else>
        В данный момент нет зарегистрированных участников
    </#list>

</@c.page>