<#macro addSchool>

      <form id="addSchool" method="post" action="addschool">
            <div class="form-group row">
                <input type="text" class="col-9 form-control" id="schoolName" name="schoolName" aria-describedby="school" placeholder="Введите название школы" required />
                <button form="addSchool" type="submit" class="col-3 btn btn-primary">Добавить</button>
            </div>
      </form>

</#macro>