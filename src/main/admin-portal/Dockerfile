# Install dependencies only when needed
FROM node:lts-alpine AS deps

RUN apk add --no-cache libc6-compat
WORKDIR /opt/app
COPY package*.json ./
RUN yarn install --frozen-lockfile

# Rebuild the source code only when needed
# This is where because may be the case that you would try
# to build the app based on some `X_TAG` in my case (Git commit hash)
# but the code hasn't changed.
FROM node:lts-alpine AS builder

ENV NODE_ENV=production
WORKDIR /opt/app
COPY . .
COPY --from=deps /opt/app/node_modules ./node_modules
RUN yarn build

# Production image, copy all the files and run next
FROM node:lts-alpine AS runner

ARG X_TAG
WORKDIR /opt/app

RUN addgroup -g 1001 -S nodejs
RUN adduser -S nextjs -u 1001

RUN apk update && apk add bash

COPY wait-for-backend.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh && dos2unix wait-for-it.sh

ENV NODE_ENV=production
COPY --from=builder /opt/app/public ./public
COPY --from=builder --chown=nextjs:nodejs /opt/app/.next ./.next
COPY --from=builder /opt/app/node_modules ./node_modules

USER nextjs

CMD ["node_modules/.bin/next", "start"]
