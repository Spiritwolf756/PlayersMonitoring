<#macro addPlayer>

<div class="row">
     <div class="col">
        <form id="addPlayer" method="post" action="addplayer">

             <!-- школа -->
             <div class="form-group row">
                <label for="school" class="col-3 col-form-label">Школа:</label>
                <div class="col-9">
                    <select id="school" name="school" class="form-control" required >
                        <#list schools as school>
                            <option>${school.name}</option>
                        </#list>
                    </select>
                </div>
             </div>


            <!-- фамилия -->
            <div class="form-group row">
                <label for="surname" class="col-3 col-form-label">Фамилия:</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="surname" name="surname" aria-describedby="surname" placeholder="введите фамилию" required />
                </div>
            </div>

            <!-- имя -->
             <div class="form-group row">
                <label for="name" class="col-3 col-form-label">Имя:</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="name" name="name" aria-describedby="name" placeholder="введите имя" required />
                </div>
             </div>

             <!-- отчество и т.п. -->
             <div class="form-group row">
                <label for="lastname" class="col-3 col-form-label">Отчество:</label>
                <div class="col-9">
                    <input type="text" class="form-control" id="lastname" name="lastname" aria-describedby="lastname" placeholder="введите отчество" />
                </div>
             </div>
            <button form="addPlayer" type="submit" class="btn btn-primary">Добавить</button>
        </form>
    </div>
 </div>
</#macro>