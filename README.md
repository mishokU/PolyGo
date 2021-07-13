PolyGo
===============
This project was inspired by problem with navigation in my University. 

Introduction
===============
This Poject is the sequential steps in the development of a specific application for navigation
routing at St. Petersburg Polytechnic University in the form of augmented reality. The structure of
specific approaches, architectural and division into modules is described. To begin with, the choice
was made towards a clean architecture and a lot of modularity, since in the development of this
program, this approach solves many problems, such as: the assembly is faster, the code becomes more
readable, and different people are responsible for different modules.

Tasks to solve
===============
  1) Create simple screens with useful information about example campus and teachers.
  2) Create search tab on this teachers and campuses store in the local database.
  3) Create AR Module that will help students to find entrance in the campus.
  4) Create Yandex map if telephone do not have AR modules.
  5) Create bookmarks for teachers and campuses.

Implementation
===============
There are a lot of fragments to work on Single Activity pattern. First of all i created an app module, that contains CompositeHolder pattern in own module that manage all dependencies in dagger 2.  This module also have some Bases classes, main logic to all components and utils classes. The next step was to create local database with room component with api and impl modules to store our data like teachers and buildings. After i created core-components that contains bottom sheets with employee and building to re-use them in different module. 

Screenshoots
===============
<img src="https://github.com/mishokU/PolyGo/blob/master/screenshoots/1_w0QeeQqrnISXLhYkYZWoAg.png?raw=true" height="400" width=auto><img src="https://github.com/mishokU/PolyGo/blob/master/screenshoots/%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8C%D0%BD%D0%BE%D1%81%D1%82%D1%8C.png?raw=true" height="400" width=auto><img src="https://github.com/mishokU/PolyGo/blob/master/screenshoots/%D0%BD%D0%B0%D0%B2%D0%B8%D0%B3%D0%B0%D1%86%D0%B8%D1%8F.png?raw=true" height="400" width=auto>
