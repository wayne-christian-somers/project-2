const nodeRSA = require('node-rsa');

export default class WalmartUtil {

  generateWalmartAuthHeaders = (consumerId: string, keyVer: string, privateKey: string) => {
    const hashList = {
        "WM_CONSUMER.ID": consumerId,
        "WM_CONSUMER.INTIMESTAMP": Date.now(),
        "WM_SEC.KEY_VERSION": keyVer,
    };

    const sortedHashString = `${hashList["WM_CONSUMER.ID"]}\n${hashList["WM_CONSUMER.INTIMESTAMP"]}\n${hashList["WM_SEC.KEY_VERSION"]}\n`;
    const signer = new nodeRSA(privateKey, "pkcs1");
    const signature = signer.sign(sortedHashString);
    const signature_enc = signature.toString("base64");

    return {
        "WM_SEC.AUTH_SIGNATURE": signature_enc,
        "WM_CONSUMER.INTIMESTAMP": hashList["WM_CONSUMER.INTIMESTAMP"],
        "WM_CONSUMER.ID": hashList["WM_CONSUMER.ID"],
        "WM_SEC.KEY_VERSION": hashList["WM_SEC.KEY_VERSION"],
    };
  }

}
