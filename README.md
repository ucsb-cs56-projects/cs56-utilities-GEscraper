cs56-utilities-GEscraper
========================

Project Information
---------------
```
 YES | mastergberry | Scrape UCSB course pages for appripriate classes that fulfill certain GE requirements
```
####Purpose
------------------
1. Scrape and show General Education Area Course list. 
2. Scrape and show courses offered by a specific department in College of Engineering

The user has the option to choose to display courses from general subject or engineering.

The user can choose to show the course description of each course.


####What's being scraped?
-------------------------------
The example websites that are being scraped are:
http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/AreaE.aspx  (general education)
https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/coe/compengr.aspx?DeptTab=Courses  (engineering)

####Who made what changed?
----------------------------------
*Changes by Dylan Lynch and Brent Kirkland*
- **DONE**: [Test for getting a specific department from an area](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/11) - *50 pts*
- **DONE**: [Help Feature](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/10) - *100 pts*
- **DONE**: [Better Formatting - Usability](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/8) - *100 pts*
- **DONE**: [Better user documentation](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/5) - *100 pts*
- **DONE**: [Allow for more sorting options](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/6) - *200 pts*
- **DONE**: [Allow reuse](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/4) - *50 pts*
- **DONE**: [Beautification - updated comments,  future refractoring, and readme (beautiful, right?)](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/14) - *25 pts*

*Recent Changes of LAST LAST GROUP (W15)*

- Improved Javadoc
- Added JUnit Tests
- Refactored files for Git
- Fixed missing class case
- Added User interaction to input an Area (eg "B", "C")

*Recent Changes of LAST GROUP (W16)*

- Added a basic GUI
- Converted the code to use Jsoup (which in turn beautified the code and made it simpler)

*Notes from W16*

- Any changes to the codebase from this point forward will probably not involve the existing code, because there isn't much left to do with it
- Code changes will likely be new features or expanding the scraper to do other things

*Changes by Xinjie You and Xingyuan Lin (F16)*
- **DONE**: [Scrape other services(scrape courses offered by a specific department in CoE)](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/21) - *400 pts*
- **DONE**: [Refactor main class](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/27) - *200 pts*
- **DONE**: [Give course description](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/26) - *400 pts(500 pts?)*


####*Notes from F16*
----------------------------------
**A major mistake we made and a valuable lesson we learned**

We put a lot of efforts working on [issue#21](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/21), which says "*Expand the existing scraper to also scrape other UCSB services. Like the College of Engineering's GEAR book.*" However, unfortunately, we misunderstood the issue and did all the work to scrape courses that are offered by College of Engineering, no matther whether or not they belong to GE. Because we think it is part of "*other UCSB services*".

Actually, we should have noticed this because the name of the project is **GEscraper**!! But we are so focused on the code and development that we ignored this problem.

So, the lesson we learned is that we write code to meet customers' needs. There should be a good system to pass information about customers' needs down to software development. The communications and cooperations among different roles in a software team are very important. 

**Advice for future groups**
- Since the work we did on [issue#21](https://github.com/UCSB-CS56-Projects/cs56-utilities-GEscraper/issues/21) does not belong to the project, it should be removed. However, the code we wrote are not meaningless in respect to code itself. Maybe groups working on project [cs56-scrapers-ucsb-curriculum](https://github.com/UCSB-CS56-Projects/cs56-scrapers-ucsb-curriculum) can refer to the code to make future developments.
- In order to get course description according to the course title, we have to map the title to correct url. Unluckily, there is no general rule, so we have to create the mapping table by hand. It is done in the file *AreaUrlMappingTable.java*. When reviewing our code, a kind lady brought up a question about whether it is more appropriate to put the mapping table into a configure file, rather than writing it in the code. We think the answer is yes, so that we do not to recompile the code if the mapping table is changed. Maybe you can work on this matter to improve the quality of the code.
- There is some reused code. Future groups can further refactor the code by creating more general classes that others inherit from.
- The GUI uses FlowLayout now, future groups can change the code to make the GUI looks same in different computer.
- The fisrt GUI window can't be closed now. Future groups can fix this bug.

F17 Final Remarks

This code is mainly set up in the EngGUI, ScraperGUI, and GeGUI. ScraperGUI contains the main method. The other two GUI files rely on the scraper files (GetEngInfo, GetGeInfo) to pull course information from external websites and display it to the user. We also implemented a mashup with UCSB Curriculum scraper. Currently, the UCSB Curriculum scraper files are in our repo. In the future, those files should be removed and instead the project should be used as a library. In the future, more details on enrollment history for past quarters could be added as well. Error handling could be implemented as well for when scraping fails (a course isn't found based on the filters entered). In general, make sure you understand what the purpose of each file is before trying to modify or use it. Make comments to document any changes you make.
