export interface Car {
	id :  CarId,
	title : CarTitle,
	description : CarDescription,
	brand : CarBrand,
	carImageURL: CarUrl,
	carAnnouncementUrl : CarUrl,
	price : CarPrice
}

interface CarId {
    value : String // esto seria un string pero de un UUID, lo ids se generarán en el cliente
}

interface CarTitle {
    value : String
}

interface CarDescription {
    value : String
}

interface CarBrand {
    value : String
}

interface CarUrl {
    value : String
}

interface CarPrice {
    value : Number
}