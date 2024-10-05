<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Регистрация читателя</h1>

    <#if errorMessage??>
        <div class="alert alert-danger">${errorMessage}</div>
    </#if>

    <form action="/auth/register" method="POST" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="surname" class="form-label">Фамилия:</label>
            <input type="text" class="form-control" id="surname" name="surname" value="${userDTO.surname!''}" required>
            <div class="invalid-feedback">Пожалуйста, введите Фамилию.</div>
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Имя:</label>
            <input type="text" class="form-control" id="name" name="name" value="${userDTO.name!''}" required>
            <div class="invalid-feedback">Пожалуйста, введите Имя.</div>
        </div>

        <div class="mb-3">
            <label for="patronymic" class="form-label">Отчество:</label>
            <input type="text" class="form-control" id="patronymic" name="patronymic" value="${userDTO.patronymic!''}">
        </div>

        <div class="mb-3">
            <label for="address" class="form-label">Адрес:</label>
            <input type="text" class="form-control" id="address" name="address" value="${userDTO.address!''}" required>
            <div class="invalid-feedback">Пожалуйста, введите адрес.</div>
        </div>

        <div class="mb-3">
            <label for="passportNumber" class="form-label">Номер паспорта:</label>
            <input type="text" class="form-control" id="passportNumber" name="passportNumber" value="${userDTO.passportNumber!''}" required>
            <div class="invalid-feedback">Пожалуйста, введите номер паспорта.</div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <div class="invalid-feedback">Пожалуйста, введите пароль.</div>
        </div>

        <div class="mb-3">
            <label for="confirmPassword" class="form-label">Подтвердите пароль:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            <div class="invalid-feedback">Пожалуйста, подтвердите пароль.</div>
        </div>

        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
    })()
</script>
</body>
</html>
