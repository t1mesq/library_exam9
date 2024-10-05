<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Вход</h1>

    <#if errorMessage??>
        <div class="alert alert-danger">${errorMessage}</div>
    </#if>

    <form action="/auth/login" method="POST" class="needs-validation" novalidate>
        <div class="mb-3">
            <label for="readerTicketNumber" class="form-label">Читательский билет:</label>
            <input type="text" class="form-control" id="readerTicketNumber" name="readerTicketNumber" required>
            <div class="invalid-feedback">Пожалуйста, введите номер читательского билета.</div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Пароль:</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <div class="invalid-feedback">Пожалуйста, введите пароль.</div>
        </div>

        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
