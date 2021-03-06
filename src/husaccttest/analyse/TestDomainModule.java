package husaccttest.analyse;

import java.util.HashMap;
import java.util.List;

import husacct.analyse.AnalyseServiceImpl;
import husacct.common.dto.AnalysedModuleDTO;

public class TestDomainModule extends TestCaseExtended{

	private AnalyseServiceImpl service;
	
	public void setUp(){
		service = new AnalyseServiceImpl();
	}
	
	public void testGetRootModules(){
		int totalModulesExpected = 2;
		
		AnalysedModuleDTO[] modules = service.getRootModules();
		assertEquals(totalModulesExpected, modules.length);
		
		String domainNameExpected = "domain";
		String domainUniqueNameExpected = "domain";
		int domainSubmoduleCount = 0;
		String domainTypeExpected = "package";
		
		String infrastructureNameExpected = "infrastructure";
		String infrastructureUniqueNameExpected = "infrastructure";
		int infrastructureSubmoduleCount = 0;
		String infrastructureTypeExpected = "package";
		
		HashMap<String, Object> domainExpectedModule = createModuleHashmap(
				domainNameExpected, domainUniqueNameExpected, domainSubmoduleCount, domainTypeExpected);
		
		HashMap<String, Object> infrastructureExpectedModule = createModuleHashmap(
				infrastructureNameExpected, infrastructureUniqueNameExpected, infrastructureSubmoduleCount, infrastructureTypeExpected);
		
		boolean domainFoundModule = compaireDTOWithValues(domainExpectedModule, modules);
		boolean infrastructureFoundModule = compaireDTOWithValues(infrastructureExpectedModule, modules);
		assertEquals(true, domainFoundModule);
		assertEquals(true, infrastructureFoundModule);
	}
	
	public void testGetChildrenOfPackageModule(){
		int totalModulesExpected = 2;
		String modulesFrom = "domain.locationbased";
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom);
		assertEquals(totalModulesExpected, modules.length);
		
		String foursquareNameExpected = "foursquare";
		String foursquareUniqueNameExpected = modulesFrom + ".foursquare";
		int foursquareSubmodulesExpected = 4;
		String foursquareTypeExpected = "package";
		
		String latitudeNameExpected = "latitude";
		String latitudeUniqueNameExpected = modulesFrom + ".latitude";
		int latitudeSubmodulesExpected = 3;
		String latitudeTypeExpected = "package";
		
		HashMap<String, Object> foursquareExpectedModule = createModuleHashmap(
				foursquareNameExpected, foursquareUniqueNameExpected, foursquareSubmodulesExpected, foursquareTypeExpected);
		HashMap<String, Object> latitudeExpectedModule = createModuleHashmap(
				latitudeNameExpected, latitudeUniqueNameExpected, latitudeSubmodulesExpected, latitudeTypeExpected);
		
		boolean foursquareFoundModule = compaireDTOWithValues(foursquareExpectedModule, modules);
		boolean latitudeFoundModule = compaireDTOWithValues(latitudeExpectedModule, modules);
		assertEquals(true, foursquareFoundModule);
		assertEquals(true, latitudeFoundModule);
	}
	
	public void testGetChildrenOfClassModule(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.locationbased.foursquare.Account";
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetChildrenOfNotExistingModule(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.notExisting";
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetChilderenOfPackageWithDepthOne(){
		int totalModulesExpected = 2;
		String modulesFrom = "domain.locationbased";
		int depth = 1;
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
		
		String foursquareNameExpected = "foursquare";
		String foursquareUniqueNameExpected = modulesFrom + "." + foursquareNameExpected;
		int foursquareSubmodulesExpected = 0;
		String foursquareTypeExpected = "package";
		
		String latitudeNameExpected = "latitude";
		String latitudeUniqueNameExpected = modulesFrom + "." + latitudeNameExpected;
		int latitudeSubmodulesExpected = 0;
		String latitudeTypeExpected = "package";
		
		HashMap<String, Object> foursquareExpectedModule = createModuleHashmap(foursquareNameExpected, foursquareUniqueNameExpected, foursquareSubmodulesExpected, foursquareTypeExpected);
		HashMap<String, Object> latitudeExpectedModule = createModuleHashmap(latitudeNameExpected, latitudeUniqueNameExpected, latitudeSubmodulesExpected, latitudeTypeExpected);
		
		boolean foundFoursquare = compaireDTOWithValues(foursquareExpectedModule, modules);
		boolean foundLatitude = compaireDTOWithValues(latitudeExpectedModule, modules);
		assertEquals(true, foundFoursquare);
		assertEquals(true, foundLatitude);
	}
	
	public void testGetChildrenOfPackageWithDepthTwo(){
		int totalModulesExpected = 2;
		String modulesFrom = "domain.locationbased";
		int depth = 2;
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
		
		String foursquareNameExpected = "foursquare";
		String foursquareUniqueNameExpected = modulesFrom + "." + foursquareNameExpected;
		int foursquareSubmodulesExpected = 4;
		String foursquareTypeExpected = "package";
		
		String latitudeNameExpected = "latitude";
		String latitudeUniqueNameExpected = modulesFrom + "." + latitudeNameExpected;
		int latitudeSubmodulesExpected = 3;
		String latitudeTypeExpected = "package";
		
		HashMap<String, Object> foursquareExpectedModule = createModuleHashmap(
				foursquareNameExpected, foursquareUniqueNameExpected, foursquareSubmodulesExpected, foursquareTypeExpected);
		HashMap<String, Object> latitudeExpectedModule = createModuleHashmap(
				latitudeNameExpected, latitudeUniqueNameExpected, latitudeSubmodulesExpected, latitudeTypeExpected);
		
		boolean foundFoursquare = compaireDTOWithValues(foursquareExpectedModule, modules);
		boolean foundLatitude = compaireDTOWithValues(latitudeExpectedModule, modules);
		assertEquals(true, foundFoursquare);
		assertEquals(true, foundLatitude);
	}
	
	public void testGetChildrenOfPackageDepthZero(){
		int totalModulesExpected = 1;
		String modulesFrom = "domain";
		int depth = 0;
		
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
		
		String locationbasedNameExpected = "locationbased";
		String locationbasedUniqueNameExpected = modulesFrom + "." + locationbasedNameExpected;
		int locationbasedSubmodulesExpected = 2;
		String locationbasedTypeExpected = "package";
		
		HashMap<String, Object> locationbasedExpectedModule = createModuleHashmap(
				locationbasedNameExpected, locationbasedUniqueNameExpected, locationbasedSubmodulesExpected, locationbasedTypeExpected);
		boolean foundLocationbased = compaireDTOWithValues(locationbasedExpectedModule, modules);
		assertEquals(true, foundLocationbased);
		
		
		List<AnalysedModuleDTO> submodules = modules[0].subModules;
		int totalSubModules = 2;
		assertEquals(totalSubModules, submodules.size());
		
		
		List<AnalysedModuleDTO> foursquaresubmodules = submodules.get(0).subModules;
		int totalFoursquareSubModules = 4;
		assertEquals(totalFoursquareSubModules, foursquaresubmodules.size());
		
		List<AnalysedModuleDTO> latitudesubmodules = submodules.get(1).subModules;
		int totalLatitudeSubModules = 3;
		assertEquals(totalLatitudeSubModules, latitudesubmodules.size());
		
	}
	
	public void testGetChildrenOfClassDepthOne(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.locationbased.latitude.Account";
		int depth = 1;
	
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetChildrenOfClassDepthTwo(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.locationbased.latitude.Account";
		int depth = 2;
	
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetChildrenOfClassDepthZero(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.locationbased.latitude.Account";
		int depth = 0;
	
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetChildrenOfUnknownPackageDepthZero(){
		int totalModulesExpected = 0;
		String modulesFrom = "domain.unknown";
		int depth = 0;
	
		AnalysedModuleDTO[] modules = service.getChildModulesInModule(modulesFrom, depth);
		assertEquals(totalModulesExpected, modules.length);
	}
	
	public void testGetParentOfPackageLevelTwo(){
		String parentFrom = "domain.locationbased";
		AnalysedModuleDTO module = service.getParentModuleForModule(parentFrom);
				
		String nameExpected = "domain";
		String uniqueNameExpected = "domain";
		int submoduleExpected = 1;
		String typeExpected = "package";
		
		assertEquals(nameExpected, module.name);
		assertEquals(uniqueNameExpected, module.uniqueName);
		assertEquals(submoduleExpected, module.subModules.size());
		assertEquals(typeExpected, module.type);
		
		AnalysedModuleDTO locationbasedModule = module.subModules.get(0);
		
		String locationbasedNameExpected = "locationbased";
		String locationbasedUniquenameExpected = "domain.locationbased";
		int locationbasedSubmodulesExpected = 0;
		String locationbasedTypeExpected = "package";
		
		assertEquals(locationbasedNameExpected, locationbasedModule.name);
		assertEquals(locationbasedUniquenameExpected, locationbasedModule.uniqueName);
		assertEquals(locationbasedSubmodulesExpected, locationbasedModule.subModules.size());
		assertEquals(locationbasedTypeExpected, locationbasedModule.type);
		
	}
	
	public void testGetParentOfPackageLevelOne(){
		String parentFrom = "domain";
		AnalysedModuleDTO parentModule = service.getParentModuleForModule(parentFrom);
		
		String nameExpected = "";
		String uniqueNameExpected = "";
		int submodulesExpected = 0;
		String typeExpected = "";
		
		assertEquals(nameExpected, parentModule.name);
		assertEquals(uniqueNameExpected, parentModule.uniqueName);
		assertEquals(submodulesExpected, parentModule.subModules.size());
		assertEquals(typeExpected, parentModule.type);
	}
	
	public void testGetParentOfClassLevelFour(){
		String parentFrom = "domain.locationbased.foursquare.Account";
		AnalysedModuleDTO parentModule = service.getParentModuleForModule(parentFrom);
		
		String nameExpected = "foursquare";
		String uniquenameExpected = "domain.locationbased.foursquare";
		int totalSubmodulesExpected = 4;
		String typeExpected = "package";
		
		assertEquals(nameExpected, parentModule.name);
		assertEquals(uniquenameExpected, parentModule.uniqueName);
		assertEquals(totalSubmodulesExpected, parentModule.subModules.size());
		assertEquals(typeExpected, parentModule.type);
	}
	
	public void testGetParentOfNotExistingPackageLevelTwo(){
		String parentFrom = "domain.notExist";
		AnalysedModuleDTO parentModule = service.getParentModuleForModule(parentFrom);
		
		String nameExpected = "";
		String uniqueNameExpected = "";
		int submodulesExpected = 0;
		String typeExpected = "";
		
		assertEquals(nameExpected, parentModule.name);
		assertEquals(uniqueNameExpected, parentModule.uniqueName);
		assertEquals(submodulesExpected, parentModule.subModules.size());
		assertEquals(typeExpected, parentModule.type);
	}
	
}
