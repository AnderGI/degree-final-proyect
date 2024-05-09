export interface Car {
	id :  CarId,
	title : CarTitle,
	description : CarDescription,
	brand : CarBrand,
	carImageURL: CarUrl,
	carAnnouncementURL : CarUrl,
	price : CarPrice
}

export type CarIdUUIDTemplate = `${string}-${string}-${string}-${string}-${string}`
export interface CarId {
    value : CarIdUUIDTemplate // esto seria un string pero de un UUID, lo ids se generarán en el cliente por parte del usuario
}

interface CarTitle {
    value : String
}

interface CarDescription {
    value : String
}

export interface CarBrand {
    value : String
}

interface CarUrl {
    value : String
}

interface CarPrice {
    value : number
}