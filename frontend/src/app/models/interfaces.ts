export interface StatesFromApi {
    data: {
      CPRO: string,
      PRO: string
    }[]
}

export interface TownsFromApi {
    data: {
      CMUM: string,
      DMUN50: string
    }[]
}

export interface Email {
  textMessage: string,
  address: string,
  subject: string
}

export interface Collection {
  gameList: {
    id: number,
    apiId: number
  } []
}

export interface SellerOrBuyer {
  username: string,
  email: string,
  state: string,
  town: string,
  apiId: number,
  whatWants: string
}

export interface GameFromApi {
    id: number,
    name: string,
    description: string,
    released: Date,
    background_image: string,
    background_image_additional: string,
    rating: number,
    playtime: number,
    platforms: {
      platform: {
        id: number,
        name: string
      }
    }[],
    developers: {
      id: number,
      name: string
    }[],
    genres: {
      id: number,
      name: string
    }[],
    tags: {
      id: number,
      name: string,
      language: string,
    }[],
    publishers: {
      id: number,
      name: string,
    }[]
}