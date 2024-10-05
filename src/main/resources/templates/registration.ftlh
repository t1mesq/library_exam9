<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Форма регистрации</title>
</head>
<body>
<h1>Регистрация</h1>

<form id="registrationForm">
    <label for="surname">Фамилия:</label>
    <input type="text" id="surname" name="surname" required>

    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>

    <label for="name">Имя:</label>
    <input type="text" id="name" name="p">

    <label for="address">Адрес:</label>
    <input type="text" id="address" name="address" required>

    <label for="passportNumber">Номер паспорта:</label>
    <input type="text" id="passportNumber" name="passportNumber" required>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required>

    <label for="confirmPassword">Подтверждение пароля:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>

    <button type="submit">Получить читательский билет</button>
</form>
</body>
</html>