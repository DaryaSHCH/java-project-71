run-dist:
	./build/install/app/bin/app
setup:
	gradle wrapper --gradle-version 8.3

lint:
	./gradlew checkstyleMain checkstyleTest

report:
	./gradlew jacocoTestReport

.PHONY: build