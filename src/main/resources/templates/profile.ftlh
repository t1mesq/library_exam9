<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Библиотека</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/login">Вход</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/register">Регистрация</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center">Профиль пользователя</h1>

    <div class="mb-3">
        <h5>ФИО: ${user.surname} ${user.name} ${user.patronymic}</h5>
        <h5>Читательский билет: ${user.readerTicketNumber}</h5>
    </div>

    <h3>Взятые книги</h3>
    <table class="table">
        <thead>
            <tr>
                <th>Название книги</th>
                <th>Автор</th>
                <th>Дата возврата</th>
                <th>Статус возврата</th>
            </tr>
        </thead>
        <tbody>
            <#list bookRequests as request>
                <tr>
                    <td>${request.book.title}</td>
                    <td>${request.book.author}</td>
                    <td>${request.returnDate}</td>
                    <td>
                        <#if request.returned>
                            <span class="text-success">Возвращено</span>
                        <#else>
                            <span class="text-danger">Ожидается</span>
                        </#if>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>