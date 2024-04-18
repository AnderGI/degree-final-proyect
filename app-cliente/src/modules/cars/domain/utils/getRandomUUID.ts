// Lo implemento por ciertos errores que me estan surgiendo a raiz 

import { CarIdUUIDTemplate } from "../car/Car";

// de necesitar un polyfill de nodejs para crypto
export const mockRandomUUUID:(() => CarIdUUIDTemplate) = () => "dd8e0a85-367e-48c6-b349-e6207e21117c";