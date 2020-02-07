<#import "parts/common.ftlh" as c>

<@c.page>
<#if newSchools??>
    <#list newSchools as school>
        <div class="alert alert-success" role="alert">
            Школа <b>${school.name}</b> успешно зарегистрирована
        </div>
    </#list>
</#if>
<#if oldSchools??>
    <#list oldSchools as school>
        <div class="alert alert-danger" role="alert">
            Школа <b>${school.name}</b> не может быть добавлена. Возможно, она уже зарегистрирована
        </div>
    </#list>
</#if>
<h2>Список участвующих школ:</h2>
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Название школы</th>
    </tr>
  </thead>
  <tbody>
    <#list schools as school>
        <tr>
          <th scope="row">${school?index}</th>
          <td>${school.name}</td>
        </tr>
     <#else>
        В данный момент нет зарегистрированных школ
    </#list>

</@c.page>
