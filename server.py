from flask import Flask, render_template, request

app = Flask(__name__)

# Список вопросов и ответов
questions = [
    "Мне интересно разбираться, как работают компьютеры и технологии.",
    "Мне нравится решать сложные задачи с помощью программирования, интересно работать с алгоритмами и логикой.",
    "Мне интересно проверять, как работает приложение или сайт и находить, что можно улучшить.",
    "Мне нравится искать ошибки и недочеты в играх и приложениях.",
    "Мне интересно анализировать данные и находить в них закономерности.",
    "Мне интересно изучать, что нужно пользователям и предлагать решения для улучшения продукта.",
    "Мне нравится рисовать, меня вдохновляет создавать красивые картинки или придумывать дизайн.",
    "Мне бы хотелось разрабатывать внешний вид сайтов, приложений или игр.",
    "Мне нравится организовывать работу других людей, следить за тем, чтобы все задачи выполнялись вовремя.",
    "Мне хотелось бы помогать команде достигать целей и решать проблемы."
]

roles = {
    "Разработчик": [questions[0], questions[1]],
    "Тестер": [questions[2], questions[3]],
    "Аналитик данных": [questions[4], questions[5]],
    "Дизайнер": [questions[6], questions[7]],
    "Менеджер": [questions[8], questions[9]]
}

role_descriptions = {
    "Разработчик": "Ты любишь создавать что-то новое с помощью кода! Тебе нравится решать сложные задачи и разбираться в технологиях.",
    "Тестер": "Тебе нравится искать ошибки и делать продукт лучше. Ты внимателен к деталям и любишь, когда всё работает идеально.",
    "Аналитик данных": "Ты — исследователь и стратег! Анализируешь данные, выявляешь закономерности и помогаешь принимать решения.",
    "Дизайнер": "Ты — творец! Создаешь красивые и удобные интерфейсы, делаешь продукт привлекательным для пользователей.",
    "Менеджер": "Ты — организатор и лидер! Работаешь с людьми, следишь за сроками и помогаешь команде достигать целей."
}


@app.route('/')
def index():
    return render_template('index.html', questions=questions)


@app.route('/result', methods=['POST'])
def result():
    answers = [request.form.get(f'q{i}') for i in range(1, len(questions) + 1)]

    scores = {role: sum(1 for q in role_questions if answers[questions.index(q)] == "yes") for role, role_questions in
              roles.items()}

    best_role = max(scores, key=scores.get)
    explanation = role_descriptions.get(best_role, "Роль не определена")

    return render_template('result.html', role=best_role, description=explanation)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
