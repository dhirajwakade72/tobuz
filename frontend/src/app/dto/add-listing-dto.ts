export class AddListingDTO {
    appUserId: number = 0;
    subCategoryIds: number[] = [];
    
    price: number = 0;
    businessType: string = "";
    categoryList: string = "";
    categoryId: number = 0;
    listingDescription: string = "";
    listingKeywords: string = "";
    features: string = "";

    preferdLocation: string = "";
    preferedCity: string = "";
    preferedState:string="";

    suggestedTitle: string = "";
    title: string = "";

    contactName: string = "";
    contactNumber: string = "";
    
    favCount: number = 0;
    companyType: string = "";
    businessTurnOver: string = "";
    financialPer: string = "";
    rentAmount: string = "";
    wagesAmount: string = "";
    outgoingAmount: string = "";
    electricityAmount: string = "";
    insuranceAmount: string = "";
    gasAmount: string = "";
    moreExpence: string = "";
    grossProfit: string = "";
    netProfit: string = "";
    totalPrice: string = "";
    moreAboutBusiness: string = "";
    numberOfEmployees: number = 0;
    numberOfYearsinTrading: string = "";
    tradingHours: string = "";
    businessListingStatus: string = "";
    isoCode: string = "";
    id: number = 0;
    listingFor: string = "";
    countryName: string = "";
    businessListingId: string = "";
    webSiteUrl: string = "";
    logoFile: ListingFileDTO = new ListingFileDTO();
    listingGallary: ListingFileDTO[] = [];    
    businessTurnover: string = "";
    businessTurnoverPer: string = "";
    businessTotalExpenses: string = "";
    businessExpensesPer: string = "";
    listingType: string = "";
    favourites: string = "";
    userRole: string = "";
    createdOn: string = "";
    franchiseType: string[] = [];
    sortByTitle: boolean = false;
    sortByPrice: boolean = false;
    searchKey: string = "";
    countryIds: string[] = [];
    stateIds: string[] = [];
    countryId: number = 0;
    stateId: number = 0;
    prefredCountryId: string = "";
    isAddedToFavourites: boolean = false;
  }
  
  export class ListingFileDTO {
    fileName: string = "";
    fileUrl: string = "";
  }
  