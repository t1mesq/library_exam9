<form id="registrationForm">
    <label for="surname">Фамилия:</label>
    <input type="text" id="surname" name="surname" required>

    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>

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

<script>
    document.getElementById("registrationForm").onsubmit = async function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        const response = await fetch('/api/auth/register', {
            method: 'POST',
            body: JSON.stringify({
                surname: formData.get('surname'),
                name: formData.get('name'),
                address: formData.get('address'),
                passportNumber: formData.get('passportNumber'),
                password: formData.get('password'),
                confirmPassword: formData.get('confirmPassword')
            }),
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            const result = await response.json();
            alert(`Читательский билет успешно создан! Номер билета: ${result.ticketNumber}`);
        } else {
            const error = await response.json();
            alert(`Ошибка: ${error.message}`);
        }
    };
</script>
