# AndroidAcademy
Привет. Этот репозиторий используется под домашнее задание курса AndroidAcademy. Данный курс прохожу
уже в тот момент, когда он завершился, поэтому всю работу делаю самостоятельно по изученному материалу.
Ниже распишу по пунктам каждое задание, а их было 5.

Домашнее задание #1:

Создать репозиторий под проект на GitHube - Done.

----------------------------------------------------------------------------------------------------

Домашнее задание #2:

[Ссылка на домашнее задание](https://docs.google.com/document/d/e/2PACX-1vQ0axff0WGoR9pgNA-QnYHQFy_Vc6yzqTjPNesRIpEYY89p3OzBqd0SJVgQxHJ7isxxjL2hXWo8_8Fp/pub)
Сверстать экран, который будет отображаться при открытии информации о фильме - Done.

Макет экрана с размерами взять из [Figma](https://www.figma.com/file/p3e0HZexHmxwQaN9NcwAD9/Android-Academy?node-id=0%3A72)

XML: fragment_movies_details.xml

Используются:

    - ConstraintLayout;

    - ScrollView;

    - Guideline (begin/end - 16dp);

    - RatingBar;

    - Barrier (x3 После картинки, после заголовка с рейтингом и перед актерами)

    - RecyclerView (для нижнего модуля, где будут актеры) - заменил в следующих заданиях.

Как должно быть:

![До](https://github.com/Semyon-Ivakaev/AndroidAcademy/tree/master/readme_image/homework1.png)

![После](https://github.com/Semyon-Ivakaev/AndroidAcademy/tree/master/readme_image/homework1_done.png)

---------------------------------------------------------------------------------------------------

Домашнее задание #3:

[Ссылка на домашнее задание](https://docs.google.com/document/d/e/2PACX-1vRinJc51_6FSPPjN11LvWY8sJmL44uQzeks2wpg-OtptXXhV4I48aGWsHsuVbsHIbPdSB1xfNvQZPJ_/pub)
Сверстать дополнительный экран, на котором будет фильм. При нажатии на этот фильм будет открываться
экран с информацией о фильме.

Данное задание было переделано. На страницу с фильмами добавлен RecyclerView, окно с фильмом
вынесен во view_holder_movie.xml

XML:

    - view_holder_movie.xml;

    - fragment_movies_list.xml.

Экраны переделаны из Activity во Fragment. Приложение делается по принципу SingleActivity:

    - MainActivity;

    - FragmentMoviesList;

    - FragmentMoviesDetails.

---------------------------------------------------------------------------------------------------

Домашнее задание #4:

[Ссылка на домашнее задание](https://docs.google.com/document/d/e/2PACX-1vSTGNZY1BKh_SzzmxKy4ruyFc9nHE_DbVkY9Pj89cLsFlEQs_GKOfKAlOiutd_BNBfLQCRv3flPF6uR/pub)

[Ссылка на макет в Figma](https://www.figma.com/file/p3e0HZexHmxwQaN9NcwAD9/Android-Academy?node-id=0%3A72)

Реализовать в приложении 2 RecyclerView(задание переделано под 5ю часть, в которой данные берутся
из файлов .JSON):

    - Первый RecyclerView отображает список фильмов на первом экране.

    - Второй RecyclerView отображает список актеров на экране с детальной информацией о фильме.

Весь контент пока берется из Figma(все картинки, именна, текст).

XML:

    - view_holder_movie.xml, xml в которой сделан макет фильмов, которые будут отображены на первом экране.

    - view_holder_actor.xml, xml в которой сделан макет актеров(фото + имя).

RecyclerView для фильмов включает:

    - AllFilmsAdapter;

    - AllFilmsViewHolder.

RecyclerView для актеров:

    - ActorsAdapter;

    - ActorsViewHolder.

----------------------------------------------------------------------------------------------------

Домашнее задание #5:

[Ссылка на домашнее задание](https://docs.google.com/document/d/146nTjhH58N11yfNQLdK92gN0Hfd_P1GqNB9Bg8NH9Do/edit)

Заполнить контент приложения данными из .JSON файлов. А так же загружать картинки использую бибилотеку
[Glide](https://code.tutsplus.com/ru/tutorials/code-an-image-gallery-android-app-with-glide--cms-28207)

Файлы находятся в папке "assets":

    - data.json - содержит всю информацию о фильме, включая ссылки на постеры.

    - genres.json - содержит жанры фильмов, в data.json лежат массивы с id у каждого из фильма,
    необходимо по этим id получить название жанров из genres.json.

    - people.json - содержит информацию об актерах, в data.json лежат массивы с id у каждого из актеров,
    необходимо по этим id получить имя актера и его фотографию.

Для работы с файлами .json использую библиотеку
[moshi](https://github.com/square/moshi) о том, как работать с .json в курсе не было рассказано,
выбор пал на эту библиотеку, так как ее рекомендуют из за простоты и удобства в использовании.

data классы:

    - Film, содержит все необходмые поля, которые будем парсить.

    - Genre, жанры фильмов.

    - Actor.

Классы для парсинга файлов .json:

    -