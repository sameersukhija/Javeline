'''
This script is used to generate the QA environment POM file, from the release email:

	* database-structure:         Ver. 2.4.1.4.14
	* actruleFramework:           Ver. 2.4.1.11.0
	* actruleClient:              Ver. 2.4.1.11.0
	* actruleServer:              Ver. 2.4.1.4.23
	* StatsManager:               Ver. 2.4.1.11.0
	* databaseAdaptor:            Ver. 2.4.1.4.11
	* eventStorage:               Ver. 2.4.1.4.5
	* Documentation:              Ver. 2.4.1.4.8 [**]
	* actruleDeploymentStructure: Ver. 2.4.1.11.0
	
Test one specific key using this command:
	python pom.py | grep -C 1 database-structure
	
'''

EMAIL_PART_TEXT = '''\
* database-structure:         Ver. 2.4.1.4.14
* actruleFramework:           Ver. 2.4.1.11.0
* actruleClient:              Ver. 2.4.1.11.0
* actruleServer:              Ver. 2.4.1.4.23
* StatsManager:               Ver. 2.4.1.11.0
* databaseAdaptor:            Ver. 2.4.1.4.11 [**]
* eventStorage:               Ver. 2.4.1.4.5
* Documentation:              Ver. 2.4.1.4.8
* actruleDeploymentStructure: Ver. 2.4.1.11.0 [**]
'''

import re

def main():
	f = open('pom.xml')
	pom = f.read()
	f.close()
	
	versions = {}
	
	for line in EMAIL_PART_TEXT.split('\n'):
		cols = re.split('\s+', line)
		if len(cols) < 4:
			continue
		
		artifact_id = cols[1].strip(':')
		version = cols[3]
		
		versions[artifact_id] = version
	
	print pom % versions

main()
